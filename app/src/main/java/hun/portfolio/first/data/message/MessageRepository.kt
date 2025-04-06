package hun.portfolio.first.data.message

interface MessageRepository {
    suspend fun getMessages(chatId: Long): List<MessageEntity>
    suspend fun getMessages(): List<MessageEntity>
    suspend fun getLastMessage(chatId: Long): MessageEntity?

    suspend fun addMessage(
        chatId: Long,
        content: String,
        authorName: String,
        timestamp: String
    )

    suspend fun sendMessage(
        chatId: Long,
        content: String,
        authorName: String
    ): MessageResponse

    suspend fun getAIMessage(chatId: Long): AiMessageResponse
    suspend fun getAIProfileImage(): AiProfileImageResponse
    suspend fun initChatbot(chatRoomId: Long): BaseResponse<Boolean>
}
