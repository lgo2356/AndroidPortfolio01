package hun.portfolio.first.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hun.portfolio.first.data.message.MessageEntity
import hun.portfolio.first.data.messageByMe
import hun.portfolio.first.data.messageByOther

private val chatBubbleShape = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)

@Composable
fun ChatItemBubble(
    content: String,
    isUserMe: Boolean,
) {
    val bubbleBgColor = if (isUserMe) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Column {
        Surface(
            color = bubbleBgColor,
            shape = chatBubbleShape
        ) {
            Text(
                text = content,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun ChatItemBubbleMePreview() {
    ChatItemBubble(
        content = messageByMe.content,
        isUserMe = true
    )
}

@Preview
@Composable
fun ChatItemBubbleOtherPreview() {
    ChatItemBubble(
        content = messageByOther.content,
        isUserMe = false
    )
}
