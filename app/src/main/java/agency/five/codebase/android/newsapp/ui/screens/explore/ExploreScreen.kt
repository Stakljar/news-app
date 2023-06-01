package agency.five.codebase.android.newsapp.ui.screens.explore

import agency.five.codebase.android.newsapp.R
import agency.five.codebase.android.newsapp.ui.components.Introduction
import agency.five.codebase.android.newsapp.ui.theme.spacing
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview

private const val TextBeforeUrlLength = 15

private const val url = "https://mediastack.com"
private const val urlLength = 22

@Composable
fun ExploreRoute(modifier: Modifier = Modifier, onNavigateToSearch: () -> Unit, viewModel: ExploreViewModel) {
    val newsViewState by viewModel.newsViewState.collectAsState()
    if(newsViewState != null){
        DefaultNewsScreen(newsViewState = newsViewState!!)
    }
    else{
        ExploreScreen(modifier = modifier, onNavigateToSearch = onNavigateToSearch)
    }
}

@Composable
fun ExploreScreen(modifier: Modifier = Modifier, onNavigateToSearch: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    Box(modifier = modifier.fillMaxSize()) {
        Introduction(
            modifier = Modifier.align(alignment = Alignment.Center),
            onClick = onNavigateToSearch
        )
        ClickableText(
            text = AnnotatedString(
                text = stringResource(id = R.string.info_about_source),
                spanStyles = listOf(
                    AnnotatedString.Range(
                        SpanStyle(
                            fontStyle = FontStyle.Italic,
                            textDecoration = TextDecoration.Underline
                        ),
                        start = TextBeforeUrlLength,
                        end = urlLength + TextBeforeUrlLength
                    ),
                    AnnotatedString.Range(
                        SpanStyle(color = MaterialTheme.colors.onSurface),
                        start = 0,
                        end = urlLength + TextBeforeUrlLength
                    )
                ),
            ),
            onClick = { uriHandler.openUri(url) },
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(end = MaterialTheme.spacing.small, bottom = MaterialTheme.spacing.small),
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
@Preview
fun ExplorePreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colors.background) {
            ExploreScreen(onNavigateToSearch = { })
        }
    }
}

