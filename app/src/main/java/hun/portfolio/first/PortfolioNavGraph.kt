package hun.portfolio.first

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hun.portfolio.first.ui.HomeScreen
import hun.portfolio.first.ui.chat.ChatScreen

@Composable
fun PortfolioNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigateToChat: () -> Unit = {},
    startDestination: String = PortfolioDestination.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = PortfolioDestination.HOME_ROUTE
        ) {
            HomeScreen(
                navigateToChat,
                modifier
            )
        }

        composable(
            route = PortfolioDestination.CHAT_ROUTE
        ) {
            ChatScreen(modifier)
        }
    }
}
