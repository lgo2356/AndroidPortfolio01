package hun.portfolio.first.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import hun.portfolio.first.PortfolioNavGraph
import hun.portfolio.first.PortfolioNavActions
import hun.portfolio.first.ui.theme.PortfolioTheme

@Composable
fun PortfolioApp(modifier: Modifier = Modifier) {
    PortfolioTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            PortfolioNavActions(navController)
        }

        PortfolioNavGraph(
            navigateToChat = navigationActions.navigateToChat,
            modifier = modifier,
            navController = navController
        )
    }
}
