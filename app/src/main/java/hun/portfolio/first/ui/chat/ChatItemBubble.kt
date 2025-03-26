package hun.portfolio.first.ui.chat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hun.portfolio.first.data.messageByMe
import hun.portfolio.first.data.messageByOther

private val chatBubbleShape = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)

@Composable
fun ChatItemBubble(
    content: String,
    timestamp: String,
    isUserMe: Boolean,
) {
    val bubbleBgColor = if (isUserMe) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Row(verticalAlignment = Alignment.Bottom) {
        if (isUserMe) {
            Text(
                text = timestamp,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.padding(2.dp))
        }

        Surface(
            color = bubbleBgColor,
            shape = chatBubbleShape,
            modifier = Modifier.sizeIn(maxWidth = 200.dp)
        ) {
            Text(
                text = content,
                modifier = Modifier.padding(16.dp)
            )
        }

        if (!isUserMe) {
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = timestamp,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
fun ChatItemBubbleMePreview() {
    ChatItemBubble(
        content = messageByMe.content,
        timestamp = "13:30",
        isUserMe = true
    )
}

@Preview
@Composable
fun ChatItemBubbleOtherPreview() {
    ChatItemBubble(
        content = messageByOther.content,
        timestamp = "13:30",
        isUserMe = false
    )
}
