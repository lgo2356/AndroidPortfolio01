package hun.portfolio.first.data.message.impl

import hun.portfolio.first.data.message.MessageDao
import hun.portfolio.first.data.message.MessageEntity
import hun.portfolio.first.data.message.MessageRepository

class MessageRepositoryImpl(
    private val messageDao: MessageDao,
) : MessageRepository {
    override suspend fun getMessages(): List<MessageEntity> {
        return messageDao.getAllMessages()
    }

    override suspend fun addMessage(message: MessageEntity) {
        messageDao.addMessage(message)
    }
}
