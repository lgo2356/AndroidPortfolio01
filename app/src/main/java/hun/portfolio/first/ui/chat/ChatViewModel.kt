package hun.portfolio.first.ui.chat

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hun.portfolio.first.data.message.MessageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ChatUiState(
    val messages: List<MessageUiState> = mutableStateListOf(),
)

class ChatViewModel(
    private val messageRepository: MessageRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        refreshAll()
    }

    fun addMessage(messageState: MessageUiState) {
        _uiState.value = _uiState.value.copy(
            messages = listOf(messageState) + _uiState.value.messages
        )

        viewModelScope.launch {
            val response = messageRepository.sendMessage(
                content = messageState.content,
                authorName = messageState.authorName,
                timestamp = messageState.timestamp
            )
        }

//        if (data != null) {
//            val reMessage = MessageEntity(
//                author = "assistant",
//                content = data.content,
//                timestamp = data.formattedTimestamp
//            )
//
//            _uiState.value.messages.add(0, reMessage)
//        }
    }

    private fun refreshAll() {
        viewModelScope.launch {
            val messages = messageRepository.getMessages()

            _uiState.value = _uiState.value.copy(
                messages = messages.map { message ->
                    MessageUiState(
                        content = message.content,
                        authorName = message.author,
                        authorImage = message.authorImage,
                        timestamp = message.timestamp,
                    )
                }
            )

//            for (index in messages.indices) {
//                val message = messages[index]
////                val nextAuthor = messages.getOrNull(index + 1)?.author
////                val isLastMessageByAuthor = nextAuthor != message.author
//
//                _uiState.value.messages.add(messageState)
//            }
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
