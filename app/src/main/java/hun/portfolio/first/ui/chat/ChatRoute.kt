package hun.portfolio.first.ui.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hun.portfolio.first.data.message.MessageEntity

@Composable
fun ChatRoute(chatViewModel: ChatViewModel) {
    val uiState by chatViewModel.uiState.collectAsStateWithLifecycle()

    ChatScreen(
        messages = uiState.messages,
        onMessageSent = { content ->
            chatViewModel.addMessage(
                MessageEntity(
                    author = "me",
                    content = content,
                    timestamp = "8:08 PM",
                )
            )
        }
    )
}
