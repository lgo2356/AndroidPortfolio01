package hun.portfolio.first.data.message.impl

import hun.portfolio.first.data.ApiService
import hun.portfolio.first.data.message.MessageDao
import hun.portfolio.first.data.message.MessageEntity
import hun.portfolio.first.data.message.MessageRepository
import hun.portfolio.first.data.message.MessageRequest
import hun.portfolio.first.data.message.MessageResponse
import retrofit2.Response

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

    override suspend fun sendMessage(message: String): Response<MessageResponse> {
        val req = MessageRequest(message)

        return apiService.sendMessage(req)
    }
}
