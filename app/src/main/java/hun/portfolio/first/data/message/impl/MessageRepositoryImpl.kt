package hun.portfolio.first.data.message.impl

import hun.portfolio.first.data.ApiService
import hun.portfolio.first.data.message.AiMessageRequest
import hun.portfolio.first.data.message.AiMessageResponse
import hun.portfolio.first.data.message.AiMessageResponseData
import hun.portfolio.first.data.message.AiProfileImageResponse
import hun.portfolio.first.data.message.BaseResponse
import hun.portfolio.first.data.message.InitChatbotRequest
import hun.portfolio.first.data.message.InitChatbotResponse
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
    override suspend fun getMessages(chatId: Long): List<MessageEntity> {
        return messageDao.getMessages(chatId)
    }

    override suspend fun getMessages(): List<MessageEntity> {
        return messageDao.getAllMessages()
    }

    override suspend fun getLastMessage(chatId: Long): MessageEntity? {
        return messageDao.getLastMessage(chatId)
    }

    override suspend fun addMessage(
        chatId: Long,
        content: String,
        authorName: String,
        timestamp: String
    ) {
        val entity = MessageEntity(
            chatId = chatId,
            content = content,
            userName = authorName,
            timestampYYYYMMdd = timestamp,
            timestampHHmm = timestamp,
            timestampFull = timestamp,
            profileImagePath = ""
        )

        messageDao.addMessage(entity)
    }

    override suspend fun sendMessage(
        chatId: Long,
        content: String,
        authorName: String
    ): MessageResponse {
        // 서버에 메시지 전송
        val response = apiService.sendMessage(
            MessageRequest(
                chatRoomId = chatId.toString(),
                content = content,
            )
        )

        if (response.isSuccessful) {
            // 서버 전송이 성공하면 로컬 DB에 저장
            if (response.code() == 200) {
                val dateParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault())
                val dateFormatter1 = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
                val dateFormatter2 = SimpleDateFormat("HH:mm", Locale.getDefault())
                val dateFormatter3 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
                val rawTimestamp: String? = response.body()?.data?.timestamp
                val timestampYYYYMMdd: String
                val timestampHHmm: String
                val timestampFull: String

                if (rawTimestamp != null) {
                    val date: Date = dateParser.parse(rawTimestamp) ?: Date()

                    timestampYYYYMMdd = dateFormatter1.format(date)
                    timestampHHmm = dateFormatter2.format(date)
                    timestampFull = dateFormatter3.format(date)
                } else {
                    timestampYYYYMMdd = "error_timestamp"
                    timestampHHmm = "error_timestamp"
                    timestampFull = "error_timestamp"
                }

                messageDao.addMessage(
                    MessageEntity(
                        chatId = chatId,
                        content = content,
                        userName = authorName,
                        timestampYYYYMMdd = timestampYYYYMMdd,
                        timestampHHmm = timestampHHmm,
                        timestampFull = timestampFull,
                        profileImagePath = ""
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

    override suspend fun getAIMessage(chatId: Long): AiMessageResponse {
        val response = apiService.getMessageFromAI(
            request = AiMessageRequest(
                chatRoomId = chatId.toString()
            )
        )

        return if (response.isSuccessful) {
            if (response.code() == 200) {
                val data: AiMessageResponseData = response.body()?.data!!

                val dateParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault())
                val dateFormatter1 = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
                val dateFormatter2 = SimpleDateFormat("HH:mm", Locale.getDefault())
                val dateFormatter3 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
                val rawTimestamp: String? = response.body()?.data?.timestamp
                val timestampYYYYMMdd: String
                val timestampHHmm: String
                val timestampFull: String

                if (rawTimestamp != null) {
                    val date: Date = dateParser.parse(rawTimestamp) ?: Date()

                    timestampYYYYMMdd = dateFormatter1.format(date)
                    timestampHHmm = dateFormatter2.format(date)
                    timestampFull = dateFormatter3.format(date)
                } else {
                    timestampYYYYMMdd = "error_timestamp"
                    timestampHHmm = "error_timestamp"
                    timestampFull = "error_timestamp"
                }

                messageDao.addMessage(
                    MessageEntity(
                        chatId = chatId,
                        content = data.content,
                        userName = data.name,
                        timestampYYYYMMdd = timestampYYYYMMdd,
                        timestampHHmm = timestampHHmm,
                        timestampFull = timestampFull,
                        profileImagePath = ""
                    )
                )
            } else if (response.code() == 204) {
                //TODO: Handle empty body.
            } else {
                //TODO: Throw error message.
            }

            response.body()!!
        } else {
            AiMessageResponse(
                code = response.code().toString(),
                message = "Error body.",
                request = null,
                data = null,
            )
        }
    }

    override suspend fun getAIProfileImage(): AiProfileImageResponse {
        val response = apiService.getAIProfileImage()

        return if (response.isSuccessful) {
            if (response.code() == 200) {
                response.body()?.data!!
            } else if (response.code() == 204) {
                //TODO: Handle empty body.
            } else {
                //TODO: Throw error message.
            }

            response.body()!!
        } else {
            AiProfileImageResponse(
                code = response.code().toString(),
                message = "Error body.",
                data = null,
            )
        }
    }

    override suspend fun initChatbot(chatRoomId: Long): BaseResponse<Boolean> {
        val response = apiService.initChatbot(
            request = InitChatbotRequest(
                chatRoomId = chatRoomId.toString()
            )
        )

        return if (response.isSuccessful) {
            if (response.code() == 200) {
                response.body()?.data!!
            } else if (response.code() == 204) {
                //TODO: Handle empty body.
            } else {
                //TODO: Throw error message.
            }

            response.body()!!
        } else {
            InitChatbotResponse(
                code = response.code().toString(),
                message = "Error body.",
                request = "",
                data = false,
            )
        }
    }
}
