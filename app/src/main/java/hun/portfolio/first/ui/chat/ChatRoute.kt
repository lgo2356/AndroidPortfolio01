package hun.portfolio.first.ui.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ChatRoute(chatViewModel: ChatViewModel) {
    val messageViewModels by chatViewModel.messageViewModels.collectAsStateWithLifecycle()

    ChatScreen(
        chatViewModel = chatViewModel,
        messageViewModels = messageViewModels,
        onMessageSent = { content ->
            chatViewModel.addMessage(
                MessageUiState(
                    content = content,
                    authorName = "me",
                    timestamp = "",
                )
            )
        }
    )
}
