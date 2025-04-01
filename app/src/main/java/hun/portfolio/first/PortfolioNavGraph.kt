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
import hun.portfolio.first.ui.chatList.ChatListRoute
import hun.portfolio.first.ui.chatList.ChatListViewModel
import hun.portfolio.first.ui.login.LoginRoute
import hun.portfolio.first.ui.login.LoginViewModel

@Composable
fun PortfolioNavGraph(
    appContainer: AppContainer,
    navController: NavHostController = rememberNavController(),
    navigateToChat: (Long) -> Unit = {},
    navigateToChatList: () -> Unit = {},
    startDestination: String = PortfolioDestination.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier
    ) {
        composable(route = PortfolioDestination.HOME_ROUTE) {
            val loginViewModel: LoginViewModel = viewModel(
                factory = LoginViewModel.provideFactory(
                    userRepository = appContainer.userRepository,
                )
            )

            LoginRoute(
                loginViewModel = loginViewModel,
                navigateToChatList = navigateToChatList,
            )
        }

        composable(route = PortfolioDestination.CHAT_LIST_ROUTE) {
            val viewModel: ChatListViewModel = viewModel(
                factory = ChatListViewModel.provideFactory(
                    chatRepository = appContainer.chatRepository,
                    messageRepository = appContainer.messageRepository
                )
            )

            ChatListRoute(
                viewModel = viewModel,
                navigateToChat = navigateToChat
            )
        }

        composable(
            route = "${PortfolioDestination.CHAT_ROUTE}/{chatId}",
            arguments = listOf(
                navArgument("chatId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val chatId = backStackEntry.arguments?.getLong("chatId")!!

            val chatViewModel: ChatViewModel = viewModel(
                factory = ChatViewModel.provideFactory(
                    chatRepository = appContainer.chatRepository,
                    messageRepository =  appContainer.messageRepository,
                    chatId = chatId
                )
            )

            ChatRoute(chatViewModel = chatViewModel)
        }
    }
}
