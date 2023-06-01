package agency.five.codebase.android.newsapp.ui.components

import agency.five.codebase.android.newsapp.mock.Mock
import agency.five.codebase.android.newsapp.R
import agency.five.codebase.android.newsapp.params.Params
import agency.five.codebase.android.newsapp.ui.theme.NewsAppTheme
import agency.five.codebase.android.newsapp.ui.theme.spacing
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview

data class PreferenceCardViewState(
    val id: Int,
    val keyword: String,
    val categories: List<String>,
    val countries: List<String>,
    val languages: List<String>,
    val isDefault: Boolean
)

@Composable
fun PreferenceCard(
    modifier: Modifier = Modifier,
    preferenceCardState: PreferenceCardViewState,
    deletePreference: (Int) -> Unit,
    onNavigateToPreferenceEdit: (Int) -> Unit,
    addDefault: (Int) -> Unit
) {
    Card(modifier = modifier.clickable { onNavigateToPreferenceEdit(preferenceCardState.id) }) {
        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.extra),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = stringResource(id = R.string.delete_pref),
                modifier = Modifier
                    .align(End)
                    .clickable { deletePreference(preferenceCardState.id) }
                    .size(dimensionResource(id = R.dimen.icon_size)),
                tint = MaterialTheme.colors.onSurface
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.keyword),
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Text(
                    text =
                        if (preferenceCardState.keyword != null) {
                            if (preferenceCardState.keyword.length > 19) {
                                preferenceCardState.keyword.substring(0, 19) + "…"
                            } else {
                                preferenceCardState.keyword
                            }
                        } else {
                            ""
                        },
                    modifier = Modifier.align(CenterHorizontally),
                    textAlign = TextAlign.Center
                )
            }
            PreferenceCardText(
                preferenceTypeValues = Params.getCategories(),
                values = preferenceCardState.categories,
                title = stringResource(id = R.string.categories),
                modifier = Modifier.fillMaxWidth()
            )
            PreferenceCardText(
                preferenceTypeValues = Params.getCountries(),
                values = preferenceCardState.countries,
                title = stringResource(id = R.string.countries),
                modifier = Modifier.fillMaxWidth()
            )
            PreferenceCardText(
                preferenceTypeValues = Params.getLanguages(),
                values = preferenceCardState.languages,
                title = stringResource(id = R.string.languages),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.default))
            Column {
                Divider()
                if (!preferenceCardState.isDefault) {
                    Text(
                        text = stringResource(id = R.string.set_default),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .clickable { addDefault(preferenceCardState.id) }
                            .align(CenterHorizontally)
                            .padding(vertical = MaterialTheme.spacing.default),
                        style = TextStyle(textDecoration = TextDecoration.Underline)
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string._default),
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(vertical = MaterialTheme.spacing.default),
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PreferenceCardPreview() {
    NewsAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            PreferenceCard(
                preferenceCardState = Mock.getPref(),
                onNavigateToPreferenceEdit = { },
                deletePreference = { },
                addDefault = { }
            )
        }
    }
}
