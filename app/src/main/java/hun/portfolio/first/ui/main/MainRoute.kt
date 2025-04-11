package hun.portfolio.first.ui.main

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hun.portfolio.first.ui.main.chatList.ChatListRoute
import hun.portfolio.first.ui.main.chatList.ChatListViewModel

@Composable
fun MainRoute(
    mainViewModel: MainViewModel,
    chatListViewModel: ChatListViewModel,
    navigateToChat: (Long) -> Unit
) {
    val mainUiState = mainViewModel.uiState.collectAsStateWithLifecycle()

    MainScreen(
        uiState = mainUiState.value,
        chatListRoute = { ChatListRoute(chatListViewModel, navigateToChat) },
        onSelectedItem = { index ->
            mainViewModel.onSelectedBottomBarItem(index)
        }
    )
}
