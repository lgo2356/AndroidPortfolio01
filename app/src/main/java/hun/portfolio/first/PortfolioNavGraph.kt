package hun.portfolio.first

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hun.portfolio.first.data.AppContainer
import hun.portfolio.first.ui.chat.ChatRoute
import hun.portfolio.first.ui.chat.ChatViewModel
import hun.portfolio.first.ui.login.LoginRoute
import hun.portfolio.first.ui.login.LoginViewModel
import hun.portfolio.first.ui.main.MainRoute
import hun.portfolio.first.ui.main.MainViewModel
import hun.portfolio.first.ui.main.chatList.ChatListViewModel

@Composable
fun PortfolioNavGraph(
    appContainer: AppContainer,
    navController: NavHostController = rememberNavController(),
    navigateToMain: () -> Unit = {},
    navigateToChat: (Long) -> Unit = {}
) {
    NavHost(
        navController = navController,
        startDestination = NavDestination.WELCOME_ROUTE,
        modifier = Modifier
    ) {
        composable(route = NavDestination.WELCOME_ROUTE) {
            val loginViewModel: LoginViewModel = viewModel(
                factory = LoginViewModel.provideFactory(
                    userRepository = appContainer.userRepository,
                )
            )

            LoginRoute(
                loginViewModel = loginViewModel,
                navigateToMain = navigateToMain,
            )
        }

        composable(route = NavDestination.MAIN_ROUTE) {
            val mainViewModel: MainViewModel = viewModel(
                factory = MainViewModel.provideFactory()
            )

            val chatListViewModel: ChatListViewModel = viewModel(
                factory = ChatListViewModel.provideFactory(
                    chatRepository = appContainer.chatRepository,
                    messageRepository = appContainer.messageRepository
                )
            )

            MainRoute(
                mainViewModel = mainViewModel,
                chatListViewModel = chatListViewModel,
                navigateToChat = navigateToChat
            )
        }

        composable(
            route = "${NavDestination.CHAT_ROUTE}/{chatId}",
            arguments = listOf(
                navArgument("chatId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val chatId = backStackEntry.arguments?.getLong("chatId")!!

            val chatViewModel: ChatViewModel = viewModel(
                factory = ChatViewModel.provideFactory(
                    chatRepository = appContainer.chatRepository,
                    messageRepository = appContainer.messageRepository,
                    chatId = chatId
                )
            )

            ChatRoute(chatViewModel = chatViewModel)
        }
    }
}
