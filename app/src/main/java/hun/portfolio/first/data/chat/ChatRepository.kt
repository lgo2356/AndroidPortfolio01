package hun.portfolio.first.data.chat

interface ChatRepository {
    suspend fun addChat(name: String): Long
    suspend fun getChat(id: Long): ChatEntity
    suspend fun getChats(): List<ChatEntity>
}