package hun.portfolio.first.data.message

import com.google.gson.annotations.SerializedName

data class AiProfileImageResponse(
    val code: String,
    val message: String,

    @SerializedName("response")
    val data: AiProfileImageResponseData?
)

data class AiProfileImageResponseData(
    val base64: String
)