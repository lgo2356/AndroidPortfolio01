package hun.portfolio.first.data.message

import com.google.gson.annotations.SerializedName

data class AiMessageRequest(
    @SerializedName("chat_room_id")
    val chatRoomId: String
)