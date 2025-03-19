package hun.portfolio.first.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hun.portfolio.first.data.message.MessageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel(private val messageRepository: MessageRepository) : ViewModel() {
    private val _messageViewModels = MutableStateFlow<List<MessageViewModel>>(emptyList())
    val messageViewModels: StateFlow<List<MessageViewModel>> = _messageViewModels

    init {
        refreshAll()
    }

    fun addMessage(messageState: MessageUiState) {
        val prevViewModel = messageViewModels.value.last()
        val viewModel = MessageViewModel(
            repository = messageRepository,
            initialState = messageState,
            prevState = prevViewModel.uiState.value
        )

        viewModel.send()

        _messageViewModels.update { it + listOf(viewModel) }

//        viewModelScope.launch {
//            if (data != null) {
//                val reMessage = MessageEntity(
//                    author = "assistant",
//                    content = data.content,
//                    timestamp = data.formattedTimestamp
//                )
//
//                _uiState.value.messages.add(0, reMessage)
//            }
//        }
    }

    private fun refreshAll() {
        viewModelScope.launch {
            val entities = messageRepository.getMessages()
            val tempList: MutableList<MessageViewModel> = mutableListOf()

            entities.mapIndexed { index, entity ->
                val prevEntity = entities.getOrNull(index - 1)

                val state = MessageUiState(
                    content = entity.content,
                    authorName = entity.author,
                    authorImage = entity.authorImage,
                    timestamp = entity.timestamp,
                )
                val prevState = if (prevEntity != null) {
                    MessageUiState(
                        content = prevEntity.content,
                        authorName = prevEntity.author,
                        authorImage = prevEntity.authorImage,
                        timestamp = prevEntity.timestamp
                    )
                } else {
                    null
                }

                val viewModel = MessageViewModel(
                    repository = messageRepository,
                    initialState = state,
                    prevState = prevState
                )
                tempList.add(viewModel)
            }

            _messageViewModels.update { tempList }
        }
    }

    companion object {
        fun provideFactory(messageRepository: MessageRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return ChatViewModel(messageRepository = messageRepository) as T
                }
            }
    }
}
