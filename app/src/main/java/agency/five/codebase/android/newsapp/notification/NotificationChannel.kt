package agency.five.codebase.android.newsapp.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi

private const val name = "News channel"
private const val descriptionText = "Channel for notifications used for reminder to check news"
const val CHANNEL_ID = "NEWS_01"

@RequiresApi(Build.VERSION_CODES.O)
val channel = NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
    description = descriptionText
}
