package hun.portfolio.first.data.message

import androidx.room.Entity
import androidx.room.PrimaryKey
import hun.portfolio.first.R

@Entity(tableName = "message_table")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val chatId: Long = -1,
    val content: String,

//    @Embedded(prefix = "user_")
//    val user: UserEntity,

    val userName: String,
    val timestampYYYYMMdd: String,
    val timestampHHmm: String,
    val timestampFull: String,
    val profileImagePath: String,
    val authorImage: Int = if (userName == "me") R.drawable.ali else R.drawable.someone_else,
)
