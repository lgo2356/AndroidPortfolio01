package hun.portfolio.first.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import hun.portfolio.first.ui.theme.PortfolioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: @Composable () -> Unit
) {
    TopAppBar(
        title = title,
    )
}

@Preview
@Composable
fun AppBarPreview() {
    PortfolioTheme {
        AppBar(title = {
            Text(
                text = "Preview",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        })
    }
}
