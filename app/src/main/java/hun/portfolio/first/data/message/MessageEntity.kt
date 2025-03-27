package hun.portfolio.first.data.message

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import hun.portfolio.first.R
import hun.portfolio.first.data.user.UserEntity

@Entity(tableName = "message_table")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val content: String,

//    @Embedded(prefix = "user_")
//    val user: UserEntity,

    val userName: String,
    val timestampYYYYMMdd: String,
    val timestampHHmm: String,
    val profileImagePath: String,
    val authorImage: Int = if (userName == "me") R.drawable.ali else R.drawable.someone_else,
)
