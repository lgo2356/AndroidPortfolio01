package hun.portfolio.first

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object PortfolioDestination {
    const val HOME_ROUTE = "home"
    const val CHAT_ROUTE = "chat"
}

class PortfolioNavActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {

    }

    val navigateToChat: () -> Unit = {
        navController.navigate(PortfolioDestination.CHAT_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            launchSingleTop = true
            restoreState = true
        }
    }
}
