package hun.portfolio.first.data.message

import hun.portfolio.first.data.ApiResult
import hun.portfolio.first.ui.chat.MessageData

interface MessageRepository {
    suspend fun getMessages(): ApiResult<List<MessageData>>
    suspend fun addMessage(message: MessageData): ApiResult<String>
}
