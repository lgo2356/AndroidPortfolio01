package hun.portfolio.first.ui.chatList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hun.portfolio.first.data.chat.ChatEntity
import hun.portfolio.first.data.chat.ChatRepository
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

class ChatListViewModel(private val chatRepository: ChatRepository) : ViewModel() {
    private val _uiEvent = Channel<ChatListUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(ChatListUiState(chatStates = emptyList()))
    val uiState: StateFlow<ChatListUiState> = _uiState

    init {
        refresh()
    }

    fun addChat() {
        viewModelScope.launch {
            val entity = ChatEntity(
                name = "0",
                createdTime = "20250328"
            )

            chatRepository.addChat(entity)

            val newChatState = ChatUiState(channelTitle = entity.name)
            val newChatStates = _uiState.value.chatStates + listOf(newChatState)

            _uiState.update { ChatListUiState(chatStates = newChatStates) }
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            val entities = chatRepository.getChats()
            val tempList: MutableList<ChatUiState> = mutableListOf()

            for (entity in entities) {
                val chatState = ChatUiState(
                    channelTitle = entity.name
                )

                tempList.add(chatState)
            }

            _uiState.update { ChatListUiState(chatStates = tempList) }
        }
    }

    companion object {
        fun provideFactory(chatRepository: ChatRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return ChatListViewModel(chatRepository = chatRepository) as T
                }
            }
    }
}