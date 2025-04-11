package hun.portfolio.first.ui.main.chatList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ChatListRoute(
    viewModel: ChatListViewModel,
    navigateToChat: (Long) -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    ChatListScreen(
        uiState = uiState.value,
        onChatContentClick = { id -> navigateToChat(id) },
        onFloatingButtonClick = { viewModel.addChat() }
    )

    LifecycleObserver(viewModel = viewModel)

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { evt ->
            when (evt) {
                is ChatListUiEvent.NavigateToChat -> {
//                    navigateToChat()
                }
            }
        }
    }
}