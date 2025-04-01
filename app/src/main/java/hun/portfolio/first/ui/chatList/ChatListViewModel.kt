package hun.portfolio.first.ui.chatList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hun.portfolio.first.data.chat.ChatRepository
import hun.portfolio.first.data.message.MessageEntity
import hun.portfolio.first.data.message.MessageRepository
import hun.portfolio.first.extension.toBitmap
import hun.portfolio.first.ui.chat.ChatUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class ChatListUiEvent {
    data object NavigateToChat : ChatListUiEvent()
}

data class ChatListUiState(
    val chatStates: List<ChatUiState>
)

class ChatListViewModel(
    private val chatRepository: ChatRepository,
    private val messageRepository: MessageRepository
) : ViewModel() {
    private val _uiEvent = Channel<ChatListUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(ChatListUiState(chatStates = emptyList()))
    val uiState: StateFlow<ChatListUiState> = _uiState

    init {
        refresh()
    }

    fun addChat() {
        viewModelScope.launch {
            val entityId = chatRepository.addChat("Hanni")
            val bitmap = messageRepository.getAIProfileImage().data?.base64?.toBitmap()

            val newChatState = ChatUiState(
                id = entityId,
                channelTitle = "Hanni",
                profileImage = bitmap
            )
            val newChatStates = _uiState.value.chatStates + listOf(newChatState)

            _uiState.update { ChatListUiState(chatStates = newChatStates) }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            val entities = chatRepository.getChats()
            val tempList: MutableList<ChatUiState> = mutableListOf()

            for (entity in entities) {
                val lastMessageEntity: MessageEntity? = messageRepository.getLastMessage(entity.id)
                val bitmap = messageRepository.getAIProfileImage().data?.base64?.toBitmap()

                val chatState = ChatUiState(
                    id = entity.id,
                    channelTitle = "Hanni",
                    lastMessage = lastMessageEntity?.content ?: "",
                    lastMessageTime = lastMessageEntity?.timestampHHmm ?: "",
                    profileImage = bitmap
                )

                tempList.add(chatState)
            }

            _uiState.update { ChatListUiState(chatStates = tempList) }
        }
    }

    companion object {
        fun provideFactory(
            chatRepository: ChatRepository,
            messageRepository: MessageRepository
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return ChatListViewModel(
                        chatRepository = chatRepository,
                        messageRepository = messageRepository
                    ) as T
                }
            }
    }
}