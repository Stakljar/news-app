package agency.five.codebase.android.newsapp.ui.components

import agency.five.codebase.android.newsapp.mock.Mock
import agency.five.codebase.android.newsapp.ui.theme.NewsAppTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PreferenceCardText(
    modifier: Modifier = Modifier,
    preferenceTypeValues: List<String>,
    values: List<String>,
    title: String
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.align(CenterHorizontally)
        )
        Text(
            text =
            if (values.containsAll(preferenceTypeValues)) {
                "All"
            } else if (values.size > 3) {
                values.take(3).joinToString(separator = ", ") + "…"
            } else {
                values.joinToString(separator = ", ")
            },
            modifier = Modifier.align(CenterHorizontally),
            textAlign = Center
        )
    }
}

@Composable
@Preview
fun PreferenceCardTextPreview() {
    NewsAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            PreferenceCardText(
                preferenceTypeValues = Mock.getPref().categories,
                values = Mock.getPref().categories,
                title = "Title"
            )
        }
    }
}
