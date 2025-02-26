package hun.portfolio.first.ui.chat

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hun.portfolio.first.data.message.MessageRepository
import hun.portfolio.first.data.successOr
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ChatUiState(
    val messages: SnapshotStateList<MessageData> = mutableStateListOf()
)

class ChatViewModel(
    private val messageRepository: MessageRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        refreshAll()
    }

    fun addMessage(message: MessageData) {
        _uiState.value.messages.add(0, message)

        viewModelScope.launch {
            messageRepository.addMessage(message)
        }
    }

    private fun refreshAll() {
        viewModelScope.launch {
            val messagesDeferred = async { messageRepository.getMessages() }

            val messages = messagesDeferred.await().successOr(emptyList())

            _uiState.value.messages.addAll(messages)
        }
    }

    companion object {
        fun provideFactory(
            messageRepository: MessageRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return ChatViewModel(messageRepository = messageRepository) as T
            }
        }
    }
}
