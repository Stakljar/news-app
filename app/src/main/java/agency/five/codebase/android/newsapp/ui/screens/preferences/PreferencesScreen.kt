package agency.five.codebase.android.newsapp.ui.screens.preferences

import agency.five.codebase.android.newsapp.mock.Mock
import agency.five.codebase.android.newsapp.R
import agency.five.codebase.android.newsapp.ui.components.CustomButton
import agency.five.codebase.android.newsapp.ui.components.PreferenceCard
import agency.five.codebase.android.newsapp.ui.theme.Blue
import agency.five.codebase.android.newsapp.ui.theme.LightBLue
import agency.five.codebase.android.newsapp.ui.theme.NewsAppTheme
import agency.five.codebase.android.newsapp.ui.theme.spacing
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PreferencesRoute(
    modifier: Modifier = Modifier,
    viewModel: PreferencesViewModel,
    onNavigateToPreferenceEdit: (Int) -> Unit
) {
    val preferencesViewState by viewModel.preferences.collectAsState()

    PreferencesScreen(
        modifier = modifier,
        preferencesViewState = preferencesViewState,
        onNavigateToPreferenceEdit = onNavigateToPreferenceEdit,
        deletePreference = viewModel::deletePreference,
        addDefault = viewModel::toggleFavorite
    )
}

@Composable
fun PreferencesScreen(
    modifier: Modifier = Modifier,
    preferencesViewState: PreferencesViewState,
    deletePreference: (Int) -> Unit,
    addDefault: (Int) -> Unit,
    onNavigateToPreferenceEdit: (Int) -> Unit
) {
    val transition = rememberInfiniteTransition()
    val changeColor by transition.animateColor(
        initialValue = LightBLue, targetValue = Blue, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.preference_card_width)),
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = MaterialTheme.spacing.veryLarge,
                start = MaterialTheme.spacing.veryLarge,
                end = MaterialTheme.spacing.veryLarge
            ),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.veryLarge),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.veryLarge)
    ) {
        item(key = "Add new preference", span = { GridItemSpan(maxLineSpan) }) {
            Box(modifier = Modifier) {
                CustomButton(
                    text = stringResource(id = R.string.add_new_preference),
                    onClick = { onNavigateToPreferenceEdit(0) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = changeColor,
                        contentColor = Color.White
                    )
                )
            }
        }
        items(items = preferencesViewState.preferences) { pref ->
            PreferenceCard(
                preferenceCardState = pref, modifier = Modifier
                    .height(dimensionResource(id = R.dimen.preference_card_height))
                    .width(dimensionResource(id = R.dimen.preference_card_width))
                    .clip(MaterialTheme.shapes.large)
                    .border(
                        width = dimensionResource(id = R.dimen.border_width),
                        shape = MaterialTheme.shapes.large,
                        color = MaterialTheme.colors.onSurface
                    ),
                onNavigateToPreferenceEdit = onNavigateToPreferenceEdit,
                deletePreference = deletePreference,
                addDefault = addDefault
            )
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.default))
        }
    }
}

@Composable
@Preview
fun PreferencesPreview() {
    NewsAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            PreferencesScreen(
                onNavigateToPreferenceEdit = { },
                preferencesViewState = PreferencesViewState(
                    Mock.getPrefList()
                ),
                deletePreference = { },
                addDefault = { }
            )
        }
    }
}

