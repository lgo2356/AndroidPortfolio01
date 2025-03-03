package hun.portfolio.first.data

import android.content.Context
import hun.portfolio.first.data.message.MessageRepository
import hun.portfolio.first.data.message.impl.MessageRepositoryImpl
import hun.portfolio.first.data.user.UserRepository
import hun.portfolio.first.data.user.impl.UserRepositoryImpl

interface AppContainer {
    val messageRepository: MessageRepository
    val userRepository: UserRepository
}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {
    override val messageRepository: MessageRepository by lazy {
        val messageDao = AppDatabase.getInstance(applicationContext).messageDao()
        MessageRepositoryImpl(messageDao)
    }

    override val userRepository: UserRepository by lazy {
        val userDao = AppDatabase.getInstance(applicationContext).userDao()
        UserRepositoryImpl(userDao)
    }
}
