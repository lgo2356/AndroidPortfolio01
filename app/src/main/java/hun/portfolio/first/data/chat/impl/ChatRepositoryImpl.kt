package hun.portfolio.first.data.chat.impl

import hun.portfolio.first.data.chat.ChatDao
import hun.portfolio.first.data.chat.ChatEntity
import hun.portfolio.first.data.chat.ChatRepository

class ChatRepositoryImpl(private val chatDao: ChatDao) : ChatRepository {
    override suspend fun addChat(chat: ChatEntity) {
        chatDao.addChat(chat)
    }

    override suspend fun getChats(): List<ChatEntity> {
        return chatDao.getAllChats()
    }
}