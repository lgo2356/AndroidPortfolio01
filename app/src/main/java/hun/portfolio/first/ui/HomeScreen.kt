package hun.portfolio.first.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import hun.portfolio.first.ui.theme.PortfolioTheme

@Composable
fun HomeScreen(
    navigateToChat: () -> Unit,
    modifier: Modifier
) {
    Surface(modifier) {
        LoginButtonColumn(
            navigateToChat,
            modifier = modifier
                .padding(vertical = 56.dp, horizontal = 24.dp)
        )
    }
}

@Preview
@Composable
fun PortfolioAppPreview() {
    PortfolioApp(Modifier.fillMaxSize())
}

@Composable
fun LoginButtonColumn(
    navigateToChat: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginButtonNoMember(onClick = navigateToChat)
    }
}

@Composable
fun LoginButtonNoMember(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        Text(
            text = "비회원 로그인",
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginButtonNoMemberPreview() {
    PortfolioTheme {
        LoginButtonNoMember(onClick = { Log.d("TAG", "Hello") })
    }
}
