package hun.portfolio.first.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hun.portfolio.first.data.messages

@Composable
fun ChatScreen(
    messages: List<MessageData>,
    onMessageSent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold() { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
        ) {
            Messages(
                messages = messages,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f)
            )
            UserInput(
                onMessageSent = onMessageSent
            )
        }
    }
}

@Preview
@Composable
private fun ChatScreenPreview() {
    ChatScreen(
        messages = messages,
        onMessageSent = {},
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun Messages(
    messages: List<MessageData>,
    modifier: Modifier
) {
    Surface(modifier = modifier) {
        LazyColumn(reverseLayout = true) {
            for (index in messages.indices) {
                val prevAuthor = messages.getOrNull(index - 1)?.author
                val nextAuthor = messages.getOrNull(index + 1)?.author
                val content = messages[index]
                val isFirstMessageByAuthor = prevAuthor != content.author
                val isLastMessageByAuthor = nextAuthor != content.author

                item {
                    Message(
                        message = content,
                        isUserMe = content.author == "me",
                        isFirstMessageByAuthor = isFirstMessageByAuthor,
                        isLastMessageByAuthor = isLastMessageByAuthor,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
