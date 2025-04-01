package hun.portfolio.first.data.chat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChatDao {
    @Insert
    suspend fun addChat(chat: ChatEntity): Long

    @Query("SELECT * FROM chat_table WHERE id=:id")
    suspend fun getChat(id: Long): ChatEntity

    @Query("SELECT * FROM chat_table ORDER BY createdTime ASC")
    suspend fun getAllChats(): List<ChatEntity>

    @Query("SELECT * FROM chat_table WHERE id=:id ORDER BY createdTime DESC LIMIT 1")
    suspend fun getLastMessage(id: Long): ChatEntity
}
