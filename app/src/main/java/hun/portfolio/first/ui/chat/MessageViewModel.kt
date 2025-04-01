package hun.portfolio.first.ui.chat

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hun.portfolio.first.R
import hun.portfolio.first.data.message.MessageRepository
import hun.portfolio.first.extension.toBitmap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class MessageUiState(
    val content: String,
    val authorName: String,
    val authorImage: Int = if (authorName == "me") R.drawable.ali else R.drawable.someone_else,
    val timestamp: String = "initial_timestamp",
    val date: String = "initial_date",
    val isUserMe: Boolean = authorName == "me",
    val isSending: Boolean = false,
    val isAuthorChanged: Boolean = false,
    val isTimestampChanged: Boolean = false,
    val profileImage: Bitmap? = null
)

class MessageViewModel(
    private val repository: MessageRepository,
    private val prevState: MessageUiState?,
    private val chatId: Long,
    initialState: MessageUiState,
) : ViewModel() {
    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    lateinit var onSentMessage: () -> Unit

    init {
        val authorName = _uiState.value.authorName
        val isAuthorChanged = if (prevState != null) {
            authorName != prevState.authorName
        } else {
            true
        }

        val timestamp = _uiState.value.timestamp
        val isTimestampChanged = if (prevState != null) {
            timestamp != prevState.timestamp
        } else {
            true
        }

        _uiState.update {
            it.copy(
                isSending = false,
                isAuthorChanged = isAuthorChanged,
                isTimestampChanged = isTimestampChanged
            )
        }
    }

    fun send() {
        viewModelScope.launch {
            _uiState.update { it.copy(isSending = true) }

            val response = repository.sendMessage(
                chatId = chatId,
                content = _uiState.value.content,
                authorName = _uiState.value.authorName
            )

            val authorName = _uiState.value.authorName

            val dateParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault())
            val timestamp: String = response.data?.timestamp?.let {
                val dateFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
                val date: Date = dateParser.parse(it) ?: Date()

                dateFormatter.format(date)
            } ?: "error_timestamp"
            val date: String = response.data?.timestamp?.let {
                val dateFormatter = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
                val date: Date = dateParser.parse(it) ?: Date()

                dateFormatter.format(date)
            } ?: "error_date"

            val isAuthorChanged = if (prevState != null) {
                authorName != prevState.authorName
            } else {
                true
            }

            val isTimestampChanged = if (prevState != null) {
                timestamp != prevState.timestamp
            } else {
                true
            }

            _uiState.update {
                it.copy(
                    timestamp = timestamp,
                    date = date,
                    isSending = false,
                    isAuthorChanged = isAuthorChanged,
                    isTimestampChanged = isTimestampChanged
                )
            }

            onSentMessage()
        }
    }

    fun get(onDone: () -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSending = true) }

            val bitmap = repository.getAIProfileImage().data?.base64?.toBitmap()

            val response = repository.getAIMessage(chatId = chatId)

            val content = response.data?.content ?: "error_content"

            val authorName = response.data?.name ?: "error_name"

            val dateParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault())
            val timestamp: String = response.data?.timestamp?.let {
                val dateFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
                val date: Date = dateParser.parse(it) ?: Date()

                dateFormatter.format(date)
            } ?: "error_timestamp"
            val date: String = response.data?.timestamp?.let {
                val dateFormatter = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
                val date: Date = dateParser.parse(it) ?: Date()

                dateFormatter.format(date)
            } ?: "error_date"

            val isAuthorChanged = if (prevState != null) {
                authorName != prevState.authorName
            } else {
                true
            }

            val isTimestampChanged = if (prevState != null) {
                timestamp != prevState.timestamp
            } else {
                true
            }

            _uiState.update {
                it.copy(
                    content = content,
                    authorName = authorName,
                    timestamp = timestamp,
                    date = date,
                    isSending = false,
                    isAuthorChanged = isAuthorChanged,
                    isTimestampChanged = isTimestampChanged,
                    profileImage = bitmap
                )
            }

            onDone()
        }
    }
}