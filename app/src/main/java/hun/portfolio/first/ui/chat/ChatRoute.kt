package hun.portfolio.first.ui.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ChatRoute(chatViewModel: ChatViewModel) {
    val uiState by chatViewModel.uiState.collectAsStateWithLifecycle()

    ChatScreen(
        messages = uiState.messages,
        onMessageSent = { content ->
            chatViewModel.addMessage(
                MessageUiState(
                    content = content,
                    authorName = "me",
                    timestamp = "8:08 PM",
                )
            )
        }
    )
}
