package hun.portfolio.first.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hun.portfolio.first.R
import hun.portfolio.first.data.message.MessageRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MessageUiState(
    val content: String,
    val authorName: String,
    val authorImage: Int = if (authorName == "me") R.drawable.ali else R.drawable.someone_else,
    val timestamp: String,
    val isUserMe: Boolean = authorName == "me",
    val isSending: Boolean = false,
    var isLastMessageByAuthor: Boolean = false,
)

class MessageViewModel(
    private val repository: MessageRepository,
    initialState: MessageUiState
) : ViewModel() {
    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    fun send() {
        viewModelScope.launch {
            _uiState.update { it.copy(isSending = true) }

            val response = repository.sendMessage(
                content = _uiState.value.content,
                authorName = _uiState.value.authorName,
                timestamp = _uiState.value.timestamp
            )

            delay(2000)

            _uiState.update { it.copy(isSending = false) }
        }
    }
}