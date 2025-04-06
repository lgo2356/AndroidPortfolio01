package hun.portfolio.first.data.message

data class InitChatbotResponse(
    override val code: String,
    override val message: String,
    override val request: String,
    override val data: Boolean
) : BaseResponse<Boolean>(code, message, request, data)