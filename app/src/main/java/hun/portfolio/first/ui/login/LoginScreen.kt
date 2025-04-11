package hun.portfolio.first.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hun.portfolio.first.R
import hun.portfolio.first.ui.theme.GoogleWhite
import hun.portfolio.first.ui.theme.NaverGreen

@Composable
fun LoginScreen(
    onGoogleClick: () -> Unit,
    onGuestClick: () -> Unit,
    modifier: Modifier = Modifier,
    navigateToChat: () -> Unit,
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
                    onGoogleClick = onGoogleClick,
                    onGuestClick = onGuestClick,
                    modifier = modifier,
                    navigateToChat = navigateToChat,
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onGoogleClick = {},
        onGuestClick = {},
        modifier = Modifier
    ) { }
}

@Composable
fun LoginButtonColumn(
    onGoogleClick: () -> Unit,
    onGuestClick: () -> Unit,
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
            icon = R.drawable.google_logo,
            iconSize = 18.dp,
        ) {
            onGoogleClick()
        }
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Text(
            text = "비회원 로그인",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onGuestClick() }
        )
    }
}

@Composable
fun LoginButton(
    text: String,
    textColor: Color,
    backgroundColor: Color,
    icon: Int,
    iconSize: Dp = 24.dp,
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(icon),
                modifier = Modifier
                    .size(iconSize)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            Text(
                text = text,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .weight(1f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview
@Composable
private fun LoginButtonPreview() {
    LoginButton(
        text = "네이버 계정으로 로그인",
        textColor = Color.White,
        backgroundColor = NaverGreen,
        icon = R.drawable.naver_logo,
    ) {

    }
}
