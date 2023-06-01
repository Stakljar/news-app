package agency.five.codebase.android.newsapp.ui.components

import agency.five.codebase.android.newsapp.ui.theme.Blue
import agency.five.codebase.android.newsapp.ui.theme.NewsAppTheme
import agency.five.codebase.android.newsapp.ui.theme.spacing
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = Blue,
        contentColor = Color.White
    )
) {
    Button(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
        colors = colors
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            style = MaterialTheme.typography.button
        )
    }
}

@Composable
@Preview
fun CustomButtonPreview() {
    NewsAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            CustomButton(onClick = { }, text = "Explore")
        }
    }
}
