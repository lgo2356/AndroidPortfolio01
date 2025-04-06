package hun.portfolio.first.data.message

import com.google.gson.annotations.SerializedName

data class InitChatbotRequest(
    @SerializedName("chat_room_id")
    val chatRoomId: String
)
