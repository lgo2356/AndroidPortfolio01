package hun.portfolio.first.ui.chat

import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    messageViewModels: List<MessageViewModel>,
    onMessageSent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.ime)
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .padding(paddingValue),
        ) {
            Messages(
                chatViewModel = viewModel,
                messageViewModels = messageViewModels,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f)
            )
            UserInput(onMessageSent = onMessageSent)
        }
    }
}

@Composable
fun Messages(
    chatViewModel: ChatViewModel,
    messageViewModels: List<MessageViewModel>,
    modifier: Modifier
) {
    val listState = rememberLazyListState()
    val density = LocalDensity.current
    val insets = WindowInsets.navigationBars
    val imeHeight = WindowInsets.ime.getBottom(density)
    val extraScroll = with(density) { 48.dp.toPx() + insets.getBottom(density) }

    val chatUiState = chatViewModel.uiState.collectAsState()
    val messageUiStates = messageViewModels.map {
        it.uiState.collectAsState()
    }

    Surface(modifier = modifier) {
        LaunchedEffect(messageViewModels.size) {
            if (messageViewModels.isNotEmpty()) {
                listState.scrollToItem(messageViewModels.lastIndex)
            }
        }
        LaunchedEffect(imeHeight > 0) {
            if (messageViewModels.isNotEmpty()) {
                listState.animateScrollToItem(messageViewModels.lastIndex)
                listState.animateScrollBy(imeHeight + extraScroll)
            }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(messageViewModels) { index, item ->
                val curMessageUiState = messageUiStates[index].value
                val prevMessageUiState = messageUiStates.getOrNull(index - 1)?.value

                val currentDate = curMessageUiState.date
                val prevDate = prevMessageUiState?.date

                if (currentDate != "initial_date" && currentDate != prevDate) {
                    DayHeader(currentDate)
                }

                Message(item)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun DayHeader(dayString: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = dayString,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
}

@Preview
@Composable
fun DayHeaderPreview() {
    DayHeader("2025년 3월 21일")
}
