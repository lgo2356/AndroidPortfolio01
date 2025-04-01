package hun.portfolio.first.ui.chatList

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hun.portfolio.first.R
import hun.portfolio.first.ui.chat.ChannelAppBar
import hun.portfolio.first.ui.chat.ChatUiState

@Composable
fun ChatListScreen(
    uiState: ChatListUiState,
    onChatContentClick: (Long) -> Unit = {},
    onFloatingButtonClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            ChannelAppBar(
                title = "채팅 목록"
            )
        },
        floatingActionButton = { FloatingButton(onClick = onFloatingButtonClick) }) { paddingValue ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            items(uiState.chatStates) { chatState ->
                ChatContent(
                    title = chatState.channelTitle,
                    lastMessage = chatState.lastMessage,
                    lastMessageTime = chatState.lastMessageTime,
                    profileImage = chatState.profileImage,
                    onClick = { onChatContentClick(chatState.id) }
                )
            }
        }
    }
}

@Composable
fun ChatContent(
    title: String,
    lastMessage: String,
    lastMessageTime: String,
    profileImage: Bitmap? = null,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 8.dp)
            .clickable { onClick() }
    ) {
//        Spacer(modifier = Modifier.padding(horizontal = 9.dp))
        if (profileImage != null) {
            Image(
                painter = BitmapPainter(profileImage.asImageBitmap()),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
            )
        } else {
            Image(
                painter = painterResource(R.drawable.ali),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = lastMessage,
                style = MaterialTheme.typography.labelLarge,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 9.dp))
        Text(
            text = lastMessageTime,
            style = MaterialTheme.typography.labelLarge,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            modifier = Modifier.sizeIn(maxWidth = 48.dp)
        )
    }
}

@Composable
fun FloatingButton(onClick: () -> Unit = {}) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
        )
    }
}

@Preview
@Composable
fun ChatListScreenPreview() {
    val chatStates = listOf(
        ChatUiState(),
        ChatUiState(),
        ChatUiState()
    )
    val uiState = ChatListUiState(
        chatStates = chatStates
    )

    ChatListScreen(uiState = uiState)
}

@Preview
@Composable
fun ChatContentPreview() {
    ChatContent(
        title = "initial_title",
        lastMessage = "initial_last_message",
        lastMessageTime = "20:20"
    )
}

@Preview
@Composable
fun FloatingButtonPreview() {
    FloatingButton()
}
