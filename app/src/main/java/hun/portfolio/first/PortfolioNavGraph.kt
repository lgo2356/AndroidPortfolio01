package hun.portfolio.first

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hun.portfolio.first.data.AppContainer
import hun.portfolio.first.ui.chat.ChatRoute
import hun.portfolio.first.ui.chat.ChatViewModel
import hun.portfolio.first.ui.login.LoginRoute
import hun.portfolio.first.ui.login.LoginViewModel

@Composable
fun PortfolioNavGraph(
    appContainer: AppContainer,
    navController: NavHostController = rememberNavController(),
    navigateToChat: () -> Unit = {},
    startDestination: String = PortfolioDestination.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier
    ) {
        composable(route = PortfolioDestination.HOME_ROUTE) {
            val loginViewModel: LoginViewModel = viewModel(
                factory = LoginViewModel.provideFactory(appContainer.userRepository)
            )

            LoginRoute(
                loginViewModel = loginViewModel,
                navigateToChat = navigateToChat,
            )
        }

        composable(route = PortfolioDestination.CHAT_ROUTE) {
            val chatViewModel: ChatViewModel = viewModel(
                factory = ChatViewModel.provideFactory(appContainer.messageRepository)
            )

            ChatRoute(chatViewModel = chatViewModel)
        }
    }
}
