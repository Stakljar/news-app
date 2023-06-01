package agency.five.codebase.android.newsapp.ui.components

import agency.five.codebase.android.newsapp.mock.Mock
import agency.five.codebase.android.newsapp.R
import agency.five.codebase.android.newsapp.params.Params
import agency.five.codebase.android.newsapp.ui.screens.preference_edit.onSelect
import agency.five.codebase.android.newsapp.ui.screens.preference_edit.onSelectAll
import agency.five.codebase.android.newsapp.ui.theme.NewsAppTheme
import agency.five.codebase.android.newsapp.ui.theme.spacing
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PreferenceSelect(
    title: String,
    preferenceTypeValues: List<String>,
    values: List<String>,
    onCheck: (String) -> Unit,
    onUncheck: (String) -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.h2,
        modifier = Modifier.padding(
            top = MaterialTheme.spacing.sectionSmall,
            bottom = MaterialTheme.spacing.default
        )
    )
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = stringResource(id = R.string.select_all))
        Checkbox(
            checked = values.containsAll(preferenceTypeValues),
            onCheckedChange = {
                onSelectAll(
                    preferenceTypeValues = preferenceTypeValues,
                    values = values,
                    onCheck = onCheck,
                    onUncheck = onUncheck
                )
            },
            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.onSurface)
        )
    }
    preferenceTypeValues.forEach { value ->
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = value)
            Checkbox(
                checked = values.contains(value),
                onCheckedChange = {
                    onSelect(
                        values = values,
                        value = value,
                        onCheck = onCheck,
                        onUncheck = onUncheck
                    )
                },
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.onSurface)
            )
        }
    }
}

@Composable
@Preview
fun PreferenceSelectPreview() {
    NewsAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            PreferenceSelect(
                title = "Categories",
                preferenceTypeValues = Params.getCategories(),
                values = Mock.getPref().categories,
                onCheck = { },
                onUncheck = { }
            )
        }
    }
}
