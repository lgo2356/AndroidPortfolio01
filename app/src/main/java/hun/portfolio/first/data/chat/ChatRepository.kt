package hun.portfolio.first.data.chat

interface ChatRepository {
    suspend fun addChat(chat: ChatEntity)
    suspend fun getChats(): List<ChatEntity>
}