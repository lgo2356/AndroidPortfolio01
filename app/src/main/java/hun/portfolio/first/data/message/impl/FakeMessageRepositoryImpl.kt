package hun.portfolio.first.data.message.impl

import hun.portfolio.first.data.message.MessageEntity
import hun.portfolio.first.data.message.MessageRepository

class FakeMessageRepositoryImpl : MessageRepository {
    private val messages: MutableList<MessageEntity> =
        hun.portfolio.first.data.messages.toMutableList()

    override suspend fun getMessages(): List<MessageEntity> {
        return messages
    }

    override suspend fun addMessage(message: MessageEntity) {
        messages.add(message)
    }
}
