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
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(
    messageViewModels: List<MessageViewModel>,
    onMessageSent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
        ) {
            Messages(
                viewModels = messageViewModels,
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
    viewModels: List<MessageViewModel>,
    modifier: Modifier
) {
    Surface(modifier = modifier) {
        LazyColumn(reverseLayout = false) {
            for (viewModel in viewModels) {  // 하이, 호호, ㅀ, 4
                item {
                    Message(viewModel)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
