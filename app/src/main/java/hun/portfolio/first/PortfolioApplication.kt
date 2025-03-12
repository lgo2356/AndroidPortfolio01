package hun.portfolio.first

import android.app.Application
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import hun.portfolio.first.data.AppContainer
import hun.portfolio.first.data.AppContainerImpl

class PortfolioApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        val functions = Firebase.functions
        functions.useEmulator("10.0.2.2", 5001)

        container = AppContainerImpl(this)
    }
}
