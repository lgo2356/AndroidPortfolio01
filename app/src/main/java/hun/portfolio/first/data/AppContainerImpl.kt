package hun.portfolio.first.data

import android.content.Context
import hun.portfolio.first.data.chat.ChatRepository
import hun.portfolio.first.data.chat.impl.ChatRepositoryImpl
import hun.portfolio.first.data.message.MessageRepository
import hun.portfolio.first.data.message.impl.MessageRepositoryImpl
import hun.portfolio.first.data.user.UserRepository
import hun.portfolio.first.data.user.impl.UserRepositoryImpl

interface AppContainer {
    val messageRepository: MessageRepository
    val userRepository: UserRepository
    val chatRepository: ChatRepository
}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {
    override val messageRepository: MessageRepository by lazy {
        val messageDao = AppDatabase.getInstance(applicationContext).messageDao()
        val apiService = ApiClient.getInstance()

        MessageRepositoryImpl(
            messageDao,
            apiService,
        )
    }

    override val userRepository: UserRepository by lazy {
        val userDao = AppDatabase.getInstance(applicationContext).userDao()

        UserRepositoryImpl(userDao)
    }

    override val chatRepository: ChatRepository by lazy {
        val chatDao = AppDatabase.getInstance(applicationContext).chatDao()

        ChatRepositoryImpl(chatDao)
    }
}
