package hun.portfolio.first.ui.chat

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hun.portfolio.first.data.chat.ChatRepository
import hun.portfolio.first.data.message.MessageRepository
import hun.portfolio.first.extension.toBitmap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ChatUiState(
    val id: Long = -1,
    val channelTitle: String = "initial_title",
    val lastMessage: String = "",
    val lastMessageTime: String = "",
    val profileImage: Bitmap? = null,
)

class ChatViewModel(
    private val chatRepository: ChatRepository,
    private val messageRepository: MessageRepository,
    private val chatId: Long,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState

    private val _messageViewModels = MutableStateFlow<List<MessageViewModel>>(emptyList())
    val messageViewModels: StateFlow<List<MessageViewModel>> = _messageViewModels

    init {
        viewModelScope.launch {
            val title = chatRepository.getChat(chatId).name
            val bitmap = messageRepository.getAIProfileImage().data?.base64?.toBitmap()

            _uiState.update {
                ChatUiState(
                    channelTitle = title,
                    profileImage = bitmap
                )
            }

            refreshAll()
        }
    }

    fun addMessage(messageState: MessageUiState) {
        val prevViewModel = if (messageViewModels.value.isNotEmpty()) {
            messageViewModels.value.last()
        } else {
            null
        }
        val messageViewModel = MessageViewModel(
            repository = messageRepository,
            prevState = prevViewModel?.uiState?.value,
            chatId = chatId,
            initialState = messageState,
        )

        messageViewModel.onSentMessage = {
            val messageViewModel2 = MessageViewModel(
                repository = messageRepository,
                prevState = messageViewModel.uiState.value,
                chatId = chatId,
                initialState = MessageUiState(
                    content = "",
                    authorName = "assistant",
                    timestamp = "",
                )
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
            val entities = messageRepository.getMessages(chatId = chatId)
            val tempList: MutableList<MessageViewModel> = mutableListOf()

            entities.mapIndexed { index, entity ->
                val prevEntity = entities.getOrNull(index - 1)

                val messageUiState = MessageUiState(
                    content = entity.content,
                    authorName = entity.userName,
                    authorImage = entity.authorImage,
                    profileImage = _uiState.value.profileImage,
                    timestamp = entity.timestampHHmm,
                    date = entity.timestampYYYYMMdd,
                )
                val prevMessageUiState = prevEntity?.let {
                    MessageUiState(
                        content = it.content,
                        authorName = it.userName,
                        authorImage = it.authorImage,
                        profileImage = _uiState.value.profileImage,
                        timestamp = it.timestampHHmm,
                        date = entity.timestampYYYYMMdd
                    )
                }

                val viewModel = MessageViewModel(
                    repository = messageRepository,
                    prevState = prevMessageUiState,
                    chatId = chatId,
                    initialState = messageUiState
                )
                tempList.add(viewModel)
            }

            _messageViewModels.update { tempList }
        }
    }

    companion object {
        fun provideFactory(
            chatRepository: ChatRepository,
            messageRepository: MessageRepository,
            chatId: Long
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return ChatViewModel(
                        chatRepository = chatRepository,
                        messageRepository = messageRepository,
                        chatId = chatId
                    ) as T
                }
            }
    }
}
