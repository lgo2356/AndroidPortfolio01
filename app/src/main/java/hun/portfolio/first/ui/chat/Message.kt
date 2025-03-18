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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

data class MessageUiState(
    val content: String,
    val authorName: String,
    val authorImage: Int = if (authorName == "me") R.drawable.ali else R.drawable.someone_else,
    val timestamp: String,
    val isUserMe: Boolean = authorName == "me",
    val isSending: Boolean = false,
    var isLastMessageByAuthor: Boolean = false,
)

@Composable
fun Message(uiState: MessageUiState) {
    val borderColor = MaterialTheme.colorScheme.primary

    Row(modifier = Modifier) {
        if (uiState.isLastMessageByAuthor) {
            Image(
                modifier = Modifier
                    .size(42.dp)
                    .border(1.5.dp, borderColor, CircleShape)
                    .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
                    .clip(CircleShape)
                    .align(Alignment.Top),
                painter = painterResource(uiState.authorImage),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        } else {
            Spacer(modifier = Modifier.width(42.dp))
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Column {
            if (uiState.isLastMessageByAuthor) {
                AuthorNameTimestamp(
                    authorName = uiState.authorName,
                    timestamp = uiState.timestamp,
                )
            }
            ChatItemBubble(
                content = uiState.content,
                isUserMe = uiState.isUserMe
            )
        }

        if (uiState.isSending) {
            Spacer(modifier = Modifier.padding(4.dp))
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically),
            )
        }
    }
}

@Preview
@Composable
private fun MessageMePreview() {
    Message(messageByOther)
}

@Preview
@Composable
private fun MessageOtherPreview() {
    Message(messageByOther)
}

@Composable
private fun AuthorNameTimestamp(
    authorName: String,
    timestamp: String,
) {
    Row {
        Text(
            text = authorName,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .alignBy(LastBaseline)
                .paddingFrom(LastBaseline, after = 8.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = timestamp,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.alignBy(LastBaseline)
        )
    }
}

@Preview
@Composable
private fun AuthorNameTimestampMe() {
    AuthorNameTimestamp(messageByMe.authorName, messageByMe.timestamp)
}

@Preview
@Composable
private fun AuthorNameTimestampOther() {
    AuthorNameTimestamp(messageByMe.authorName, messageByMe.timestamp)
}
