package hun.portfolio.first.data.message

import com.google.gson.annotations.SerializedName

data class MessageResponse(
    val code: String,
    val message: String,
    val request: MessageRequest?,

    @SerializedName("response")
    val data: MessageResponseData?,
)

data class MessageResponseData(
    val timestamp: String,
    val formattedTimestamp: String,
)
