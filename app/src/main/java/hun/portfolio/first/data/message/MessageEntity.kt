package hun.portfolio.first.data.message

import androidx.room.Entity
import androidx.room.PrimaryKey
import hun.portfolio.first.R

@Entity(tableName = "message_table")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val author: String,
    val content: String,
    val timestamp: String,
    val authorImage: Int = if (author == "me") R.drawable.ali else R.drawable.someone_else,
)
