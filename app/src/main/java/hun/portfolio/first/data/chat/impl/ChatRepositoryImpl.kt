package hun.portfolio.first.data.chat.impl

import hun.portfolio.first.data.chat.ChatDao
import hun.portfolio.first.data.chat.ChatEntity
import hun.portfolio.first.data.chat.ChatRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ChatRepositoryImpl(private val chatDao: ChatDao) : ChatRepository {
    override suspend fun addChat(name: String): Long {
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
        val time = formatter.format(calendar.time)

        val entity = ChatEntity(
            name = name,
            createdTime = time
        )

        return chatDao.addChat(entity)
    }

    override suspend fun getChats(): List<ChatEntity> {
        return chatDao.getAllChats()
    }

    override suspend fun getChat(id: Long): ChatEntity {
        return chatDao.getChat(id)
    }
}