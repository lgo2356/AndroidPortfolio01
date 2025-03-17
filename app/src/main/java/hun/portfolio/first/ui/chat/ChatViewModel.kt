package hun.portfolio.first.ui.chat

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hun.portfolio.first.data.message.MessageEntity
import hun.portfolio.first.data.message.MessageRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ChatUiState(
    val messages: SnapshotStateList<MessageEntity> = mutableStateListOf(),
    val isLoading: Boolean = false,
)

class ChatViewModel(
    private val messageRepository: MessageRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        refreshAll()
    }

    fun addMessage(message: MessageEntity) {
        _uiState.value.messages.add(0, message)

        viewModelScope.launch {
            val response = messageRepository.sendMessage(message.content)
            val data = response.data

            if (data != null) {
                val reMessage = MessageEntity(
                    author = "assistant",
                    content = data.content,
                    timestamp = data.formattedTimestamp
                )

                _uiState.value.messages.add(0, reMessage)
            }

            messageRepository.addMessage(message)
        }
    }

    private fun refreshAll() {
        viewModelScope.launch {
            val messagesDeferred = async { messageRepository.getMessages() }

            val messages = messagesDeferred.await()

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
