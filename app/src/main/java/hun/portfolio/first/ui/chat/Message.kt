package hun.portfolio.first.ui.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hun.portfolio.first.R
import hun.portfolio.first.data.messageByMe
import hun.portfolio.first.data.messageByOther

@Composable
fun Message(
    message: MessageData,
    isUserMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean,
) {
    val borderColor = MaterialTheme.colorScheme.primary

    Row(modifier = Modifier) {
        if (isLastMessageByAuthor) {
            Image(
                modifier = Modifier
                    .size(42.dp)
                    .border(1.5.dp, borderColor, CircleShape)
                    .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
                    .clip(CircleShape)
                    .align(Alignment.Top),
                painter = painterResource(message.authorImage),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        } else {
            Spacer(modifier = Modifier.width(42.dp))
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Column {
            if (isLastMessageByAuthor) {
                AuthorNameTimestamp(message)
            }
            ChatItemBubble(
                message = message,
                isUserMe = isUserMe
            )
        }
    }
}

@Preview
@Composable
fun MessageMePreview() {
    Message(
        message = messageByMe,
        isUserMe = true,
        isFirstMessageByAuthor = false,
        isLastMessageByAuthor = true,
    )
}

@Preview
@Composable
fun MessageOtherPreview() {
    Message(
        message = messageByOther,
        isUserMe = false,
        isFirstMessageByAuthor = false,
        isLastMessageByAuthor = false,
    )
}

@Composable
private fun AuthorNameTimestamp(message: MessageData) {
    Row {
        Text(
            text = message.author,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .alignBy(LastBaseline)
                .paddingFrom(LastBaseline, after = 8.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = message.timestamp,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.alignBy(LastBaseline)
        )
    }
}

@Preview
@Composable
fun AuthorNameTimestampMe() {
    AuthorNameTimestamp(messageByMe)
}

@Preview
@Composable
fun AuthorNameTimestampOther() {
    AuthorNameTimestamp(messageByOther)
}

@Immutable
data class MessageData(
    val author: String,
    val content: String,
    val timestamp: String,
    val authorImage: Int = if (author == "me") R.drawable.ali else R.drawable.someone_else,
)
