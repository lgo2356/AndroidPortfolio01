package hun.portfolio.first.data

import android.content.Context
import hun.portfolio.first.data.message.MessageRepository
import hun.portfolio.first.data.message.impl.FakeMessageRepository

interface AppContainer {
    val messageRepository: MessageRepository
}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {
    override val messageRepository: MessageRepository by lazy {
        FakeMessageRepository()
    }
}
