package hun.portfolio.first

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object PortfolioDestination {
    const val HOME_ROUTE = "home"
    const val CHAT_LIST_ROUTE = "chat_list"
    const val CHAT_ROUTE = "chat"
}

class PortfolioNavActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {

    }

    val navigateToChatList: () -> Unit = {
        navController.navigate(PortfolioDestination.CHAT_LIST_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToChat: (chatId: Long) -> Unit = { id ->
        navController.navigate("${PortfolioDestination.CHAT_ROUTE}/${id}") {
            launchSingleTop = true
            restoreState = true
        }
    }
}
