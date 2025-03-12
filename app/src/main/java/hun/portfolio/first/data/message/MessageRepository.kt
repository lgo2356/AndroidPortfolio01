package hun.portfolio.first.data.message

import retrofit2.Response

interface MessageRepository {
    suspend fun getMessages(): List<MessageEntity>
    suspend fun addMessage(message: MessageEntity)
    suspend fun sendMessage(message: String): Response<MessageResponse>?
}
