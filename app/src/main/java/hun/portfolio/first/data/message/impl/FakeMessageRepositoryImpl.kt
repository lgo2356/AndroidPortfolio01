package hun.portfolio.first.data.message.impl

import hun.portfolio.first.data.message.MessageEntity
import hun.portfolio.first.data.message.MessageRepository
import hun.portfolio.first.data.message.MessageResponse

class FakeMessageRepositoryImpl : MessageRepository {
//    private val messages: MutableList<MessageEntity> =
//        hun.portfolio.first.data.messages.toMutableList()

    override suspend fun getMessages(): List<MessageEntity> {
        return listOf()
    }

    override suspend fun addMessage(content: String, authorName: String, timestamp: String) {
//        messages.add(message)
    }

    override suspend fun sendMessage(
        content: String,
        authorName: String
    ): MessageResponse {
        val emptyResponse = MessageResponse(
            code = "500",
            message = "Error body.",
            request = null,
            data = null,
        )

        return emptyResponse
    }
}
