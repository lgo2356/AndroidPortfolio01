package hun.portfolio.first.data.message.impl

import hun.portfolio.first.data.ApiResult
import hun.portfolio.first.data.message.MessageRepository
import hun.portfolio.first.ui.chat.MessageData

class FakeMessageRepository : MessageRepository {
    private val messages: MutableList<MessageData> by lazy { hun.portfolio.first.data.messages.toMutableList() }

    override suspend fun getMessages(): ApiResult<List<MessageData>> {
        return ApiResult.Success(messages)
    }

    override suspend fun addMessage(message: MessageData): ApiResult<String> {
        messages.add(0, message)

        return ApiResult.Success("success")
    }
}
