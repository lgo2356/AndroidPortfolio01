package hun.portfolio.first

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import hun.portfolio.first.ui.PortfolioApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val appContainer = (application as PortfolioApplication).container

        setContent {
            PortfolioApp(appContainer)
        }
    }
}
