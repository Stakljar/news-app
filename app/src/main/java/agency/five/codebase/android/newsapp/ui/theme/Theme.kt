package agency.five.codebase.android.newsapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Blue,
    background = VeryDarkBlue,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = DarkBlue,
    onSurface = Color.White
)

private val LightColorPalette = lightColors(
    primary = Blue,
    background = Color.White,
    onPrimary = Color.White,
    onBackground = DarkBlue,
    surface = Color.White,
    onSurface = DarkBlue
)

@Composable
fun NewsAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
