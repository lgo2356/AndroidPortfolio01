package hun.portfolio.first.ui.chatList

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ChatListScreen(viewModel: ChatListViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = { FloatingButton(onClick = { viewModel.addChat() }) }) { paddingValue ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            for (chatState in uiState.value.chatStates) {
                item {
                    ChatContent(
                        name = chatState.channelTitle
                    )
                }
            }
        }
    }
}

@Composable
fun ChatContent(
    name: String
) {
    Row {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .padding(horizontal = 16.dp),
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

}

@Preview
@Composable
fun FloatingButtonPreview() {
    FloatingButton()
}
