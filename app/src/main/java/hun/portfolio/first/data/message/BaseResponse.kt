package hun.portfolio.first.data.message

import com.google.gson.annotations.SerializedName

sealed class BaseResponse<T>(
    open val code: String,
    open val message: String,
    open val request: String,

    @SerializedName("response")
    open val data: T?
)
