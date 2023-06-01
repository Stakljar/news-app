package agency.five.codebase.android.newsapp.ui.components

import agency.five.codebase.android.newsapp.mock.Mock
import agency.five.codebase.android.newsapp.R
import agency.five.codebase.android.newsapp.ui.theme.NewsAppTheme
import agency.five.codebase.android.newsapp.ui.theme.spacing
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

private const val TextBeforeUrlLength = 14

data class NewsCardViewState(
    val title: String,
    val author: String,
    val description: String,
    val publishedAt: String,
    val imageUrl: String?,
    val source: String,
    val sourceUrl: String,
    val language: String,
    val country: String
)

@Composable
fun NewsCard(modifier: Modifier = Modifier, newsCardState: NewsCardViewState) {
    val uriHandler = LocalUriHandler.current
    Card(modifier = modifier) {
        Column {
            Text(
                text = newsCardState.title,
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(MaterialTheme.spacing.large)
            )
            Divider()
            Text(
                text = stringResource(id = R.string.author_indication) + newsCardState.author,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sectionSmall)) {
                Text(
                    text = stringResource(id = R.string.country_indication) + newsCardState.country,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large)
                )
                Text(
                    text = stringResource(id = R.string.languages_indication) + newsCardState.language,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.caption,
                )
            }
            Text(
                text = stringResource(id = R.string.published_indication) + newsCardState.publishedAt,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large)
            )
            if (newsCardState.imageUrl != null) {
                AsyncImage(
                    model = newsCardState.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.news_card_height))
                        .padding(vertical = MaterialTheme.spacing.veryLarge),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.default_image),
                    contentDescription = "Default image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.news_card_height))
                        .padding(vertical = MaterialTheme.spacing.veryLarge),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = newsCardState.description,
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.large,
                    bottom = MaterialTheme.spacing.large
                )
            )
            Divider()
            Text(
                text = stringResource(id = R.string.source_indication) + newsCardState.source,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large)
            )
            ClickableText(
                text = AnnotatedString(
                    text = stringResource(id = R.string.read_more_indication) + newsCardState.sourceUrl,
                    spanStyles = listOf(
                        AnnotatedString.Range(
                            SpanStyle(
                                fontStyle = Italic,
                                textDecoration = Underline
                            ),
                            start = TextBeforeUrlLength,
                            end = newsCardState.sourceUrl.length + TextBeforeUrlLength
                        ),
                        AnnotatedString.Range(
                            SpanStyle(color = MaterialTheme.colors.onSurface),
                            start = 0,
                            end = newsCardState.sourceUrl.length + TextBeforeUrlLength
                        )
                    )
                ),
                onClick = { uriHandler.openUri(newsCardState.sourceUrl) },
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.large,
                    bottom = MaterialTheme.spacing.large
                )
            )
        }
    }
}

@Composable
@Preview
fun NewsCardPreview() {
    NewsAppTheme {
        NewsCard(newsCardState = Mock.getNews())
    }
}
