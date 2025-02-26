package hun.portfolio.first

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hun.portfolio.first.data.AppContainer
import hun.portfolio.first.ui.HomeScreen
import hun.portfolio.first.ui.chat.ChatRoute
import hun.portfolio.first.ui.chat.ChatViewModel

@Composable
fun PortfolioNavGraph(
    appContainer: AppContainer,
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
        composable(route = PortfolioDestination.HOME_ROUTE) {
            HomeScreen(
                modifier,
                navigateToChat
            )
        }

        composable(route = PortfolioDestination.CHAT_ROUTE) {
            val chatViewModel: ChatViewModel = viewModel(
                factory = ChatViewModel.provideFactory(appContainer.messageRepository)
            )

            ChatRoute(
                chatViewModel = chatViewModel
            )
        }
    }
}
