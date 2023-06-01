package agency.five.codebase.android.newsapp.ui.screens.search

import agency.five.codebase.android.newsapp.mock.Mock
import agency.five.codebase.android.newsapp.R
import agency.five.codebase.android.newsapp.params.Params
import agency.five.codebase.android.newsapp.ui.components.CustomButton
import agency.five.codebase.android.newsapp.ui.components.FilterItem
import agency.five.codebase.android.newsapp.ui.components.NewsCard
import agency.five.codebase.android.newsapp.ui.components.PreferenceCardViewState
import agency.five.codebase.android.newsapp.ui.theme.NewsAppTheme
import agency.five.codebase.android.newsapp.ui.theme.spacing
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
) {

    val context = LocalContext.current

    val isFilterEnabled by viewModel.isFilterEnabled.collectAsState()
    val isAddPreferenceButtonShown by viewModel.isAddPreferenceButtonShown.collectAsState()
    val areCategoriesExpanded by viewModel.areCategoriesExpanded.collectAsState()
    val areCountriesExpanded by viewModel.areCountriesExpanded.collectAsState()
    val areLanguagesExpanded by viewModel.areLanguagesExpanded.collectAsState()
    val preferenceCardViewState by viewModel.preferenceViewState.collectAsState()
    val newsViewState by viewModel.newsViewState.collectAsState()
    val isSearchEmpty by viewModel.isSearchEmpty.collectAsState()

    SearchScreen(
        modifier = modifier,
        preferenceCardViewState = preferenceCardViewState,
        newsViewState = newsViewState,
        isFilterEnabled = isFilterEnabled,
        isSearchEmpty = isSearchEmpty,
        isAddPreferenceButtonShown = isAddPreferenceButtonShown,
        areCategoriesExpanded = areCategoriesExpanded,
        areCountriesExpanded = areCountriesExpanded,
        areLanguagesExpanded = areLanguagesExpanded,
        toggleCategories = viewModel::toggleCategories,
        toggleCountries = viewModel::toggleCountries,
        toggleLanguages = viewModel::toggleLanguages,
        onCategoryCheck = viewModel::insertCategory,
        onCategoryUncheck = viewModel::removeCategory,
        onCountryCheck = viewModel::insertCountry,
        onCountryUncheck = viewModel::removeCountry,
        onLanguageCheck = viewModel::insertLanguage,
        onLanguageUncheck = viewModel::removeLanguage,
        onValueChange = viewModel::changeKeywordValue,
        enableFilter = viewModel::enableFilter,
        onSearch = {
            if (preferenceCardViewState.categories.isEmpty() ||
                preferenceCardViewState.countries.isEmpty() ||
                preferenceCardViewState.languages.isEmpty()
            ) {
                Toast.makeText(
                    context,
                    "Select at least one option in every dropdown selection",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.saveSearchedState()
                viewModel.enableAddPreferenceButton()
            }
        },
        onClick = {
            viewModel.disableAddPreferenceButton()
            viewModel.insertPreference()
        }
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    preferenceCardViewState: PreferenceCardViewState,
    newsViewState: NewsViewState,
    isFilterEnabled: Boolean,
    isSearchEmpty: Boolean,
    isAddPreferenceButtonShown: Boolean,
    areCategoriesExpanded: Boolean,
    areCountriesExpanded: Boolean,
    areLanguagesExpanded: Boolean,
    toggleCategories: () -> Unit,
    toggleCountries: () -> Unit,
    toggleLanguages: () -> Unit,
    onCategoryCheck: (String) -> Unit,
    onCategoryUncheck: (String) -> Unit,
    onCountryCheck: (String) -> Unit,
    onCountryUncheck: (String) -> Unit,
    onLanguageCheck: (String) -> Unit,
    onLanguageUncheck: (String) -> Unit,
    onValueChange: (String) -> Unit,
    enableFilter: () -> Unit,
    onSearch: () -> Unit,
    onClick: () -> Unit,
) {
    Box(modifier = modifier) {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.default))
            }
            item {
                Row {
                    TextField(
                        value = preferenceCardViewState.keyword,
                        onValueChange = { onValueChange(it) },
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.search_components_height))
                            .weight(weight = 1F),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.search_news),
                            )
                        },
                        textStyle = MaterialTheme.typography.subtitle1,
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.background)
                    )
                    IconButton(onClick = onSearch) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search button",
                            modifier = Modifier
                                .height(dimensionResource(id = R.dimen.search_components_height))
                                .width(dimensionResource(id = R.dimen.search_icon_width))
                                .background(color = MaterialTheme.colors.background)
                                .padding(MaterialTheme.spacing.default),
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            }
            item {
                Column(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.veryLarge)
                ) {
                    if (isFilterEnabled) {
                        FilterItem(
                            title = stringResource(id = R.string.categories_indication),
                            preferenceTypeValues = Params.getCategories(),
                            values = preferenceCardViewState.categories,
                            onCheck = onCategoryCheck,
                            onUncheck = onCategoryUncheck,
                            expanded = areCategoriesExpanded,
                            toggleExpand = toggleCategories
                        )
                        FilterItem(
                            title = stringResource(id = R.string.countries_indication),
                            preferenceTypeValues = Params.getCountries(),
                            values = preferenceCardViewState.countries,
                            onCheck = onCountryCheck,
                            onUncheck = onCountryUncheck,
                            expanded = areCountriesExpanded,
                            toggleExpand = toggleCountries
                        )
                        FilterItem(
                            title = stringResource(id = R.string.languages_indication),
                            preferenceTypeValues = Params.getLanguages(),
                            values = preferenceCardViewState.languages,
                            onCheck = onLanguageCheck,
                            onUncheck = onLanguageUncheck,
                            expanded = areLanguagesExpanded,
                            toggleExpand = toggleLanguages
                        )
                    } else {
                        BasicTextField(
                            value = "",
                            onValueChange = { },
                            enabled = false,
                            modifier = Modifier
                                .height(IntrinsicSize.Min)
                                .padding(vertical = MaterialTheme.spacing.large)
                                .border(
                                    dimensionResource(id = R.dimen.border_width),
                                    MaterialTheme.colors.onSurface,
                                    MaterialTheme.shapes.small
                                )
                                .padding(MaterialTheme.spacing.large),
                            decorationBox = {
                                Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.veryLarge)) {
                                    Text(text = stringResource(id = R.string.filter))
                                    Icon(
                                        painter = painterResource(
                                            id = R.drawable.ic_filter
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier.clickable { enableFilter() }
                                    )
                                }
                            }
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.default))
            }
            if (!isSearchEmpty) {
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
            } else {
                item {
                    Box(
                        modifier = Modifier
                            .padding(
                                top = MaterialTheme.spacing.sectionMedium,
                                bottom = MaterialTheme.spacing.sectionVeryLarge
                            )
                    ) {
                        Text(
                            text = stringResource(id = R.string.no_results),
                            style = MaterialTheme.typography.h1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.sectionMedium))
            }
        }
        AnimatedVisibility(
            visible = isAddPreferenceButtonShown,
            enter = fadeIn(animationSpec = tween(durationMillis = 3000, easing = EaseOutExpo)),
            exit = fadeOut(animationSpec = tween(durationMillis = 1000, easing = LinearEasing)),
            modifier = Modifier
                .padding(
                    bottom = MaterialTheme.spacing.veryLarge,
                    end = MaterialTheme.spacing.veryLarge
                )
                .align(Alignment.BottomEnd)
        ) {
            CustomButton(
                text = stringResource(id = R.string.add_to_preference),
                onClick = onClick,
            )
        }
    }
}

@Composable
@Preview
fun SearchPreview() {
    NewsAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            SearchScreen(
                preferenceCardViewState = Mock.getPref(),
                newsViewState = NewsViewState(Mock.getNewsList()),
                isFilterEnabled = false,
                isAddPreferenceButtonShown = false,
                areCategoriesExpanded = false,
                areCountriesExpanded = false,
                areLanguagesExpanded = false,
                isSearchEmpty = false,
                toggleCategories = { },
                toggleCountries = { },
                toggleLanguages = { },
                onCategoryCheck = { },
                onCategoryUncheck = { },
                onCountryCheck = { },
                onCountryUncheck = { },
                onLanguageCheck = { },
                onLanguageUncheck = { },
                onValueChange = { },
                enableFilter = { },
                onSearch = { },
                onClick = { }
            )
        }
    }
}
