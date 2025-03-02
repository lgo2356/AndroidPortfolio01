package hun.portfolio.first.data.message

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageDao {
    @Query("SELECT * FROM message_table")
    suspend fun getAllMessages(): List<MessageEntity>

    @Insert
    suspend fun addMessage(message: MessageEntity)
}
