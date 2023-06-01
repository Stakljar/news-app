package agency.five.codebase.android.newsapp.ui.screens.explore

import agency.five.codebase.android.newsapp.mock.Mock
import agency.five.codebase.android.newsapp.ui.components.NewsCard
import agency.five.codebase.android.newsapp.ui.screens.search.NewsViewState
import agency.five.codebase.android.newsapp.ui.theme.NewsAppTheme
import agency.five.codebase.android.newsapp.ui.theme.spacing
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DefaultNewsScreen(modifier: Modifier = Modifier, newsViewState: NewsViewState) {
    LazyColumn(modifier = modifier) {
        items(newsViewState.newsCardViewStates) {
            NewsCard(
                newsCardState = it, modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.veryLarge,
                        vertical = MaterialTheme.spacing.large
                    )
                    .shadow(
                        elevation = MaterialTheme.spacing.veryLarge,
                        shape = MaterialTheme.shapes.medium
                    )
            )
        }
    }
}

@Composable
@Preview
fun DefaultNewsPreview() {
    NewsAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            DefaultNewsScreen(newsViewState = NewsViewState(listOf(Mock.getNews())))
        }
    }
}
