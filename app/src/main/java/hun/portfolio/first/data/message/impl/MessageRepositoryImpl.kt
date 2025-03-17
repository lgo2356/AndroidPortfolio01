package hun.portfolio.first.data.message.impl

import hun.portfolio.first.data.ApiService
import hun.portfolio.first.data.message.MessageDao
import hun.portfolio.first.data.message.MessageEntity
import hun.portfolio.first.data.message.MessageRepository
import hun.portfolio.first.data.message.MessageRequest
import hun.portfolio.first.data.message.MessageResponse

class MessageRepositoryImpl(
    private val messageDao: MessageDao,
    private val apiService: ApiService,
) : MessageRepository {
    override suspend fun getMessages(): List<MessageEntity> {
        return messageDao.getAllMessages()
    }

    override suspend fun addMessage(message: MessageEntity) {
        messageDao.addMessage(message)
    }

    override suspend fun sendMessage(content: String): MessageResponse {
        val req = MessageRequest(
            chatRoomId = "01",
            content = content,
        )

        val response = apiService.sendMessage(req)

        if (response.isSuccessful) {
            val body = response.body()

            if (body != null) {
                return body
            } else {
                val emptyResponse = MessageResponse(
                    code = "204",
                    message = "Empty body.",
                    request = null,
                    data = null,
                )

                return emptyResponse
            }
        } else {
            val emptyResponse = MessageResponse(
                code = "500",
                message = "Error body.",
                request = null,
                data = null,
            )

            return emptyResponse
        }
    }
}
