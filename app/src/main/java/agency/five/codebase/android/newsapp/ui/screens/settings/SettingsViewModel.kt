package agency.five.codebase.android.newsapp.ui.screens.settings

import agency.five.codebase.android.newsapp.notification.NotificationWorker
import agency.five.codebase.android.newsapp.notification.NotificationPreferences
import agency.five.codebase.android.newsapp.notification.PreferencesKeys
import agency.five.codebase.android.newsapp.notification.dataStore
import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

private const val NOTIFICATION_SCHEDULER_WORK_NAME = "notification-scheduler"

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val notificationWorkerRequest =
        PeriodicWorkRequestBuilder<NotificationWorker>(
            repeatInterval = 3,
            repeatIntervalTimeUnit = TimeUnit.DAYS,
            flexTimeInterval = 10,
            flexTimeIntervalUnit = TimeUnit.MINUTES
        ).build()

    val notificationPreferences =
        getApplication<Application>().applicationContext.dataStore.data
            .map { preferences ->
                val areNotificationsEnabled =
                    preferences[PreferencesKeys.ARE_NOTIFICATIONS_ENABLED] ?: false
                NotificationPreferences(areNotificationsEnabled)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(1000L),
                initialValue = NotificationPreferences(areNotificationsEnabled = false)
            )

    init {
        viewModelScope.launch {
            notificationPreferences.collect {
                if (it.areNotificationsEnabled) {
                    WorkManager.getInstance(getApplication<Application>().applicationContext)
                        .enqueueUniquePeriodicWork(
                            NOTIFICATION_SCHEDULER_WORK_NAME,
                            ExistingPeriodicWorkPolicy.KEEP,
                            notificationWorkerRequest
                        )
                } else {
                    WorkManager.getInstance(getApplication<Application>().applicationContext)
                        .cancelUniqueWork(NOTIFICATION_SCHEDULER_WORK_NAME)
                }
            }
        }
    }

    fun togglePreference(enabled: Boolean) {
        viewModelScope.launch {
            getApplication<Application>().applicationContext.dataStore.edit { preferences ->
                preferences[PreferencesKeys.ARE_NOTIFICATIONS_ENABLED] = enabled
            }
        }
    }
}
