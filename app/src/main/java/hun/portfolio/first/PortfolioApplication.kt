package hun.portfolio.first

import android.app.Application
import hun.portfolio.first.data.AppContainer
import hun.portfolio.first.data.AppContainerImpl

class PortfolioApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = AppContainerImpl(this)
    }
}
