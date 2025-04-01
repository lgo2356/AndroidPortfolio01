package hun.portfolio.first.data.chat

import androidx.room.Entity
import androidx.room.PrimaryKey
import hun.portfolio.first.ui.chat.ChatUiState

@Entity(tableName = "chat_table")
data class ChatEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val createdTime: String,
    val lastMessageTime: String = "initial_time",
)

fun ChatEntity.toChatUiState(): ChatUiState {
    return ChatUiState(
        id = id,
        channelTitle = name
    )
}