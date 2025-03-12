package hun.portfolio.first.data.message.impl

import hun.portfolio.first.data.message.MessageEntity
import hun.portfolio.first.data.message.MessageRepository
import hun.portfolio.first.data.message.MessageResponse
import retrofit2.Response

class FakeMessageRepositoryImpl : MessageRepository {
    private val messages: MutableList<MessageEntity> =
        hun.portfolio.first.data.messages.toMutableList()

    override suspend fun getMessages(): List<MessageEntity> {
        return messages
    }

    override suspend fun addMessage(message: MessageEntity) {
        messages.add(message)
    }

    override suspend fun sendMessage(message: String): Response<MessageResponse>? {
        return null
    }
}
