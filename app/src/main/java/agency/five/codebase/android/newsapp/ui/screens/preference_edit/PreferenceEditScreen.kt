package agency.five.codebase.android.newsapp.ui.screens.preference_edit

import agency.five.codebase.android.newsapp.mock.Mock
import agency.five.codebase.android.newsapp.R
import agency.five.codebase.android.newsapp.params.Params
import agency.five.codebase.android.newsapp.ui.components.CustomButton
import agency.five.codebase.android.newsapp.ui.components.PreferenceCardViewState
import agency.five.codebase.android.newsapp.ui.components.PreferenceSelect
import agency.five.codebase.android.newsapp.ui.theme.NewsAppTheme
import agency.five.codebase.android.newsapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PreferenceEditRoute(
    viewModel: PreferenceEditViewModel,
    modifier: Modifier = Modifier,
    onNavigateToPreferences: () -> Unit
) {

    val preferenceCardViewState by viewModel.preferenceViewState.collectAsState()
    val isError by viewModel.isError.collectAsState()

    PreferenceEditScreen(
        modifier = modifier,
        preferenceCardViewState = preferenceCardViewState,
        isError = isError,
        onKeywordValueChange = viewModel::changeKeywordValue,
        onClick = {
            if (preferenceCardViewState.categories.isEmpty() ||
                preferenceCardViewState.countries.isEmpty() ||
                preferenceCardViewState.languages.isEmpty()
            ) {
                viewModel.enableError()
            } else {
                viewModel.insertPreference()
                onNavigateToPreferences()
            }
        },
        onCategoryCheck = viewModel::insertCategory,
        onCategoryUncheck = viewModel::removeCategory,
        onCountryCheck = viewModel::insertCountry,
        onCountryUncheck = viewModel::removeCountry,
        onLanguageCheck = viewModel::insertLanguage,
        onLanguageUncheck = viewModel::removeLanguage,
        disableError = viewModel::disableError
    )
}

@Composable
fun PreferenceEditScreen(
    modifier: Modifier = Modifier,
    preferenceCardViewState: PreferenceCardViewState,
    isError: Boolean,
    onKeywordValueChange: (String) -> Unit,
    onClick: () -> Unit,
    onCategoryCheck: (String) -> Unit,
    onCategoryUncheck: (String) -> Unit,
    onCountryCheck: (String) -> Unit,
    onCountryUncheck: (String) -> Unit,
    onLanguageCheck: (String) -> Unit,
    onLanguageUncheck: (String) -> Unit,
    disableError: () -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(MaterialTheme.spacing.large)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.keyword),
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.default)
        )
        TextField(
            value = preferenceCardViewState.keyword,
            onValueChange = { onKeywordValueChange(it) },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.placeholder_add_pref),
                )
            },
            textStyle = MaterialTheme.typography.subtitle1,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.background),
            modifier = Modifier.height(dimensionResource(id = R.dimen.search_components_height))
        )
        PreferenceSelect(
            title = stringResource(id = R.string.categories),
            preferenceTypeValues = Params.getCategories(),
            values = preferenceCardViewState.categories,
            onCheck = onCategoryCheck,
            onUncheck = onCategoryUncheck
        )
        PreferenceSelect(
            title = stringResource(id = R.string.countries),
            preferenceTypeValues = Params.getCountries(),
            values = preferenceCardViewState.countries,
            onCheck = onCountryCheck,
            onUncheck = onCountryUncheck
        )
        PreferenceSelect(
            title = stringResource(id = R.string.languages),
            preferenceTypeValues = Params.getLanguages(),
            values = preferenceCardViewState.languages,
            onCheck = onLanguageCheck,
            onUncheck = onLanguageUncheck
        )
        if (isError) {
            Snackbar(
                action = {
                    IconButton(onClick = disableError) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "Close snackbar"
                        )
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.add_preference_empty_list_error))
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.veryLarge))
        CustomButton(
            text = stringResource(id = R.string.save),
            modifier = Modifier.align(CenterHorizontally),
            onClick = onClick
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
    }
}

@Composable
@Preview
fun PreferenceEditPreview() {
    NewsAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            PreferenceEditScreen(
                preferenceCardViewState = Mock.getPref(),
                onClick = { },
                onKeywordValueChange = { },
                onCategoryUncheck = { },
                onCategoryCheck = { },
                onCountryCheck = { },
                onCountryUncheck = { },
                onLanguageCheck = { },
                onLanguageUncheck = { },
                disableError = { },
                isError = false
            )
        }
    }
}

