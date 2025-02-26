package hun.portfolio.first.ui

import androidx.compose.foundation.clickable
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

@Composable
fun HomeScreen(
    modifier: Modifier,
    navigateToChat: () -> Unit
) {
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .padding(paddingValue)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text("Hello")
            }
            LoginButtonColumn(
                modifier = modifier,
                navigateToChat = navigateToChat
            )
        }
    }
}

@Preview
@Composable
fun PortfolioAppPreview() {
//    PortfolioApp()
}

@Composable
fun LoginButtonColumn(
    modifier: Modifier = Modifier,
    navigateToChat: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginButtonNoMember(onClick = navigateToChat)
    }
}

@Composable
fun LoginButtonNoMember(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .clickable(onClick = onClick),
        color = MaterialTheme.colorScheme.primary
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
    LoginButtonNoMember(onClick = {})
}
