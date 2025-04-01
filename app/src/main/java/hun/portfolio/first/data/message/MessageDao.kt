package hun.portfolio.first.data.message

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageDao {
    @Query("SELECT * FROM message_table WHERE chatId = :chatId")
    suspend fun getMessages(chatId: Long): List<MessageEntity>

    @Query("SELECT * FROM message_table")
    suspend fun getAllMessages(): List<MessageEntity>

    @Query("SELECT * FROM message_table WHERE chatId = :chatId ORDER BY timestampFull DESC LIMIT 1")
    suspend fun getLastMessage(chatId: Long): MessageEntity?

    @Insert
    suspend fun addMessage(message: MessageEntity)
}
