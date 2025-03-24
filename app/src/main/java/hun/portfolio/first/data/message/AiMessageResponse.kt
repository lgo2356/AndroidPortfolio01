package hun.portfolio.first.data.message

import com.google.gson.annotations.SerializedName

data class AiMessageResponse(
    val code: String,
    val message: String,
    val request: MessageRequest?,

    @SerializedName("response")
    val data: AiMessageResponseData?,
)

data class AiMessageResponseData(
    val role: String,
    val content: String,
    val name: String,
    val timestamp: String
)
