package hun.portfolio.first.ui.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ChatRoute(chatViewModel: ChatViewModel) {
    val messageViewModels by chatViewModel.messageViewModels.collectAsStateWithLifecycle()

    ChatScreen(
        messageViewModels = messageViewModels,
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
