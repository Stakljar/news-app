package agency.five.codebase.android.newsapp.notification

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

private const val PREFERENCES_NAME = "notification_preferences"
private const val ARE_NOTIFICATIONS_ENABLED_KEY_NAME = "are_notifications_enabled"

val Context.dataStore by preferencesDataStore(
    name = PREFERENCES_NAME
)

data class NotificationPreferences(
    val areNotificationsEnabled: Boolean
)

object PreferencesKeys {
    val ARE_NOTIFICATIONS_ENABLED = booleanPreferencesKey(ARE_NOTIFICATIONS_ENABLED_KEY_NAME)
}
