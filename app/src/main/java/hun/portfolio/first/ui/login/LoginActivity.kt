package hun.portfolio.first.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import hun.portfolio.first.PortfolioApplication
import hun.portfolio.first.ui.PortfolioApp

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as PortfolioApplication).container

        setContent {
            PortfolioApp(appContainer)
        }
    }
}
