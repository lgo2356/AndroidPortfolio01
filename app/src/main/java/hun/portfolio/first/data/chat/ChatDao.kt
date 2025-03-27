package hun.portfolio.first.data.chat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChatDao {
    @Insert
    suspend fun addChat(chat: ChatEntity)

    @Query("SELECT * FROM chat_table")
    suspend fun getAllChats(): List<ChatEntity>
}
