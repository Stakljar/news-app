package agency.five.codebase.android.newsapp.ui.screens.settings

import agency.five.codebase.android.newsapp.R
import agency.five.codebase.android.newsapp.notification.NotificationPreferences
import agency.five.codebase.android.newsapp.ui.theme.NewsAppTheme
import agency.five.codebase.android.newsapp.ui.theme.spacing
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsRoute(modifier: Modifier = Modifier, viewModel: SettingsViewModel) {

    val notificationPreferences by viewModel.notificationPreferences.collectAsState()
    SettingsScreen(modifier = modifier, notificationPreferences = notificationPreferences, viewModel::togglePreference)
}

@Composable
fun SettingsScreen(modifier: Modifier = Modifier, notificationPreferences: NotificationPreferences, onToggle: (Boolean) -> Unit) {
    Column(modifier = modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.sectionSmall))
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sectionVeryLarge),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.enable_notifications),
                style = MaterialTheme.typography.subtitle1
            )
            Switch(
                checked = notificationPreferences.areNotificationsEnabled,
                onCheckedChange = { onToggle(!notificationPreferences.areNotificationsEnabled) },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colors.onBackground,
                    checkedTrackColor = MaterialTheme.colors.primary
                )
            )
        }
    }
}

@Composable
@Preview
fun SettingsPreview() {
    NewsAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            SettingsScreen(
                notificationPreferences = NotificationPreferences(false),
                onToggle = { }
            )
        }
    }
}
