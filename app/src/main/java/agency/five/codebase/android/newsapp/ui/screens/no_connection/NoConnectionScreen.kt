package agency.five.codebase.android.newsapp.ui.screens.no_connection

import agency.five.codebase.android.newsapp.R
import agency.five.codebase.android.newsapp.ui.theme.NewsAppTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NoConnectionRoute(modifier: Modifier = Modifier) {
    NoConnectionScreen(modifier = modifier)
}

@Composable
fun NoConnectionScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.no_internet_dialog),
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview
fun NoConnectionPreview() {
    NewsAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            NoConnectionScreen()
        }
    }
}
