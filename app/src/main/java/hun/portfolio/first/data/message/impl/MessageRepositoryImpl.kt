package hun.portfolio.first.data.message.impl

import hun.portfolio.first.data.ApiService
import hun.portfolio.first.data.message.MessageDao
import hun.portfolio.first.data.message.MessageEntity
import hun.portfolio.first.data.message.MessageRepository
import hun.portfolio.first.data.message.MessageRequest
import hun.portfolio.first.data.message.MessageResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
            timestampYYYYMMdd = timestamp,
            timestampHHmm = timestamp
        )

        messageDao.addMessage(entity)
    }

    override suspend fun sendMessage(
        content: String,
        authorName: String
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
                val dateParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault())
                val dateFormatter1 = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
                val dateFormatter2 = SimpleDateFormat("HH:mm", Locale.getDefault())
                val rawTimestamp: String? = response.body()?.data?.timestamp
                val timestampYYYYMMdd: String
                val timestampHHmm: String

                if (rawTimestamp != null) {
                    val date: Date = dateParser.parse(rawTimestamp) ?: Date()

                    timestampYYYYMMdd = dateFormatter1.format(date)
                    timestampHHmm = dateFormatter2.format(date)
                } else {
                    timestampYYYYMMdd = "error_timestamp"
                    timestampHHmm = "error_timestamp"
                }

                messageDao.addMessage(
                    MessageEntity(
                        content = content,
                        author = authorName,
                        timestampYYYYMMdd = timestampYYYYMMdd,
                        timestampHHmm = timestampHHmm
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
