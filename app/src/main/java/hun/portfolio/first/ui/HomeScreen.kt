package hun.portfolio.first.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hun.portfolio.first.ui.theme.GoogleWhite
import hun.portfolio.first.ui.theme.KakaoYellow
import hun.portfolio.first.ui.theme.NaverGreen

@Composable
fun HomeScreen(
    modifier: Modifier,
    navigateToChat: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(color = Color.Transparent) {
            Column {
                Surface(
                    modifier = modifier.fillMaxWidth(),
                    color = Color.Transparent,
                ) {
                    Text(
                        text = "로그인",
                        textAlign = TextAlign.Center,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                }
                Spacer(Modifier.padding(vertical = 24.dp))
                LoginButtonColumn(
                    modifier = modifier,
                    navigateToChat = navigateToChat
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier) { }
}

@Composable
fun LoginButtonColumn(
    modifier: Modifier = Modifier,
    navigateToChat: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp),
    ) {
        LoginButton(
            text = "구글 계정으로 로그인",
            textColor = Color.Black,
            backgroundColor = GoogleWhite,
        ) {
            navigateToChat()
        }
        LoginButton(
            text = "카카오톡 계정으로 로그인",
            textColor = Color.Black,
            backgroundColor = KakaoYellow,
        ) {
            navigateToChat()
        }
        LoginButton(
            text = "네이버 계정으로 로그인",
            textColor = Color.White,
            backgroundColor = NaverGreen,
        ) {
            navigateToChat()
        }
    }
}

@Composable
fun LoginButton(
    text: String,
    textColor: Color,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 4.dp),
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
private fun LoginButtonPreview() {
    LoginButton(
        text = "네이버 계정으로 로그인",
        textColor = Color.White,
        backgroundColor = NaverGreen,
    ) {

    }
}
