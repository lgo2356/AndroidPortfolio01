package hun.portfolio.first

import androidx.navigation.NavHostController

object NavDestination {
    const val WELCOME_ROUTE = "welcome"
    const val MAIN_ROUTE = "main"
    const val CHAT_LIST_ROUTE = "chat_list"
    const val CHAT_ROUTE = "chat"
}

class PortfolioNavActions(navController: NavHostController) {
    val navigateToMain: () -> Unit = {
        navController.navigate(NavDestination.MAIN_ROUTE) {
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToChat: (chatId: Long) -> Unit = { id ->
        navController.navigate("${NavDestination.CHAT_ROUTE}/${id}") {
            launchSingleTop = true
            restoreState = true
        }
    }

//    val navigateToChatList: () -> Unit = {
//        navController.navigate(PortfolioDestination.CHAT_LIST_ROUTE) {
//            popUpTo(navController.graph.findStartDestination().id) {
//                saveState = true
//            }
//
//            launchSingleTop = true
//            restoreState = true
//        }
//    }
}
