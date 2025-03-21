package hun.portfolio.first.data.message

interface MessageRepository {
    suspend fun getMessages(): List<MessageEntity>
    suspend fun addMessage(content: String, authorName: String, timestamp: String)
    suspend fun sendMessage(content: String, authorName: String): MessageResponse
}
