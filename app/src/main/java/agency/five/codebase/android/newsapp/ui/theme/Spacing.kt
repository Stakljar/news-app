package agency.five.codebase.android.newsapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = 4.dp,
    val small: Dp = 2.dp,
    val large: Dp = 8.dp,
    val veryLarge: Dp = 10.dp,
    val extra: Dp = 12.dp,
    val extraLarge: Dp = 16.dp,
    val sectionSmall: Dp = 20.dp,
    val sectionMedium: Dp = 30.dp,
    val sectionLarge: Dp = 40.dp,
    val sectionVeryLarge: Dp = 70.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
