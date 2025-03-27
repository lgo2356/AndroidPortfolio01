package hun.portfolio.first.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hun.portfolio.first.data.message.MessageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ChatUiState(
    val channelTitle: String = "initial_title",
    val lastDate: String = "",
)

class ChatViewModel(
    private val messageRepository: MessageRepository,
    chatId: Long
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState

    private val _messageViewModels = MutableStateFlow<List<MessageViewModel>>(emptyList())
    val messageViewModels: StateFlow<List<MessageViewModel>> = _messageViewModels

    init {
        _uiState.update { ChatUiState(channelTitle = chatId.toString()) }

        refreshAll()
    }

    fun addMessage(messageState: MessageUiState) {
        val prevViewModel = if (messageViewModels.value.isNotEmpty()) {
            messageViewModels.value.last()
        } else {
            null
        }
        val messageViewModel = MessageViewModel(
            repository = messageRepository,
            initialState = messageState,
            prevState = prevViewModel?.uiState?.value
        )

        messageViewModel.onSentMessage = {
            val messageViewModel2 = MessageViewModel(
                repository = messageRepository,
                initialState = MessageUiState(
                    content = "",
                    authorName = "assistant",
                    timestamp = "",
                ),
                prevState = messageViewModel.uiState.value
            )

            messageViewModel2.get {
                _messageViewModels.update { it + listOf(messageViewModel2) }
            }
        }

        messageViewModel.send()

        _messageViewModels.update { it + listOf(messageViewModel) }
    }

    private fun refreshAll() {
        viewModelScope.launch {
            val entities = messageRepository.getMessages()
            val tempList: MutableList<MessageViewModel> = mutableListOf()

            entities.mapIndexed { index, entity ->
                val prevEntity = entities.getOrNull(index - 1)

                if (prevEntity != null) {
                    if (prevEntity.timestampYYYYMMdd != entity.timestampYYYYMMdd) {
                        _uiState.update { ChatUiState(lastDate = entity.timestampYYYYMMdd) }
                    }
                }

                val messageUiState = MessageUiState(
                    content = entity.content,
                    authorName = entity.userName,
                    authorImage = entity.authorImage,
                    timestamp = entity.timestampHHmm,
                    date = entity.timestampYYYYMMdd
                )
                val prevMessageUiState = prevEntity?.let {
                    MessageUiState(
                        content = it.content,
                        authorName = it.userName,
                        authorImage = it.authorImage,
                        timestamp = it.timestampHHmm,
                        date = entity.timestampYYYYMMdd
                    )
                }

                val viewModel = MessageViewModel(
                    repository = messageRepository,
                    initialState = messageUiState,
                    prevState = prevMessageUiState
                )
                tempList.add(viewModel)
            }

            _messageViewModels.update { tempList }
        }
    }

    companion object {
        fun provideFactory(
            messageRepository: MessageRepository,
            chatId: Long
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return ChatViewModel(
                        messageRepository = messageRepository,
                        chatId = chatId
                    ) as T
                }
            }
    }
}
