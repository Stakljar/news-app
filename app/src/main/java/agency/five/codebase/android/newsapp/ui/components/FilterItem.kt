package agency.five.codebase.android.newsapp.ui.components

import agency.five.codebase.android.newsapp.R
import agency.five.codebase.android.newsapp.params.Params
import agency.five.codebase.android.newsapp.ui.screens.preference_edit.onSelect
import agency.five.codebase.android.newsapp.ui.screens.preference_edit.onSelectAll
import agency.five.codebase.android.newsapp.ui.theme.NewsAppTheme
import agency.five.codebase.android.newsapp.ui.theme.spacing
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    title: String,
    preferenceTypeValues: List<String>,
    values: List<String>,
    onCheck: (String) -> Unit,
    onUncheck: (String) -> Unit,
    expanded: Boolean,
    toggleExpand: () -> Unit,
) {
    Column(modifier = modifier) {
        Text(text = title)
        BasicTextField(
            value = "",
            enabled = false,
            onValueChange = { },
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(vertical = MaterialTheme.spacing.large)
                .border(
                    dimensionResource(id = R.dimen.border_width),
                    MaterialTheme.colors.onSurface,
                    MaterialTheme.shapes.small
                )
                .padding(
                    vertical = MaterialTheme.spacing.default,
                    horizontal = MaterialTheme.spacing.large
                ),
            decorationBox = {
                Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)) {
                    Text(
                        text =
                        if (values.containsAll(preferenceTypeValues)) {
                            "All"
                        } else if (values.size > 3) {
                            values.take(3).joinToString(separator = ", ") + "…"
                        } else if (values.isNotEmpty()) {
                            values.joinToString(separator = ", ")
                        } else {
                            ""
                        }
                    )
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_dropdown
                        ),
                        contentDescription = null,
                        modifier = Modifier.clickable { toggleExpand() }
                    )
                }
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = toggleExpand,
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.dropdown_height))
        ) {
            DropdownMenuItem(
                onClick = {
                    onSelectAll(
                        preferenceTypeValues = preferenceTypeValues,
                        values = values,
                        onCheck = onCheck,
                        onUncheck = onUncheck
                    )
                }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
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
                    Text(text = stringResource(id = R.string.all))
                }
            }
            preferenceTypeValues.forEach { value ->
                DropdownMenuItem(
                    onClick = {
                        onSelect(
                            values = values,
                            value = value,
                            onCheck = onCheck,
                            onUncheck = onUncheck
                        )
                    }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
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
                        Text(text = value)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun FilterItemPreview() {
    NewsAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            FilterItem(
                title = "Categories: ",
                preferenceTypeValues = Params.getCategories(),
                values = Params.getCategories(),
                onCheck = { },
                onUncheck = { },
                expanded = true,
                toggleExpand = { }
            )
        }
    }
}
