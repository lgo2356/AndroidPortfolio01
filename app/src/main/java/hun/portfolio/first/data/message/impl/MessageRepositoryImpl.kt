package hun.portfolio.first.data.message.impl

import hun.portfolio.first.data.ApiService
import hun.portfolio.first.data.message.MessageDao
import hun.portfolio.first.data.message.MessageEntity
import hun.portfolio.first.data.message.MessageRepository
import hun.portfolio.first.data.message.MessageRequest
import hun.portfolio.first.data.message.MessageResponse

class MessageRepositoryImpl(
    private val messageDao: MessageDao,
    private val apiService: ApiService,
) : MessageRepository {
    override suspend fun getMessages(): List<MessageEntity> {
        return messageDao.getAllMessages()
    }

    override suspend fun addMessage(
        content: String,
        authorName: String,
        timestamp: String
    ) {
        val entity = MessageEntity(
            content = content,
            author = authorName,
            timestamp = timestamp,
        )

        messageDao.addMessage(entity)
    }

    override suspend fun sendMessage(
        content: String,
        authorName: String,
        timestamp: String
    ): MessageResponse {
        // 서버에 메시지 전송
        val response = apiService.sendMessage(
            MessageRequest(
                chatRoomId = "01",
                content = content,
            )
        )

        if (response.isSuccessful) {
            // 서버 전송이 성공하면 로컬 DB에 저장
            if (response.code() == 200) {
                messageDao.addMessage(
                    MessageEntity(
                        content = content,
                        author = authorName,
                        timestamp = timestamp
                    )
                )
            } else if (response.code() == 204) {
                //TODO: Handle empty body.
            } else {
                //TODO: Throw error message.
            }

            return response.body()!!
        } else {
            val emptyResponse = MessageResponse(
                code = response.code().toString(),
                message = "Error body.",
                request = null,
                data = null,
            )

            return emptyResponse
        }
    }
}
