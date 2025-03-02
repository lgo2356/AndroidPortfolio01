package hun.portfolio.first.data.message

interface MessageRepository {
    suspend fun getMessages(): List<MessageEntity>
    suspend fun addMessage(message: MessageEntity)
}
