package agency.five.codebase.android.newsapp.notification

import agency.five.codebase.android.newsapp.NewsApp
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.MutableStateFlow

val hasPermissionForNotifications =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        MutableStateFlow(
            ContextCompat.checkSelfPermission(
                NewsApp.getApplication().applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        )
    } else {
        MutableStateFlow(true)
    }
