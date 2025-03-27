package hun.portfolio.first.ui.chatList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ChatListRoute(
    viewModel: ChatListViewModel,
    navigateToChat: (Long) -> Unit
) {
    ChatListScreen(viewModel = viewModel)

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