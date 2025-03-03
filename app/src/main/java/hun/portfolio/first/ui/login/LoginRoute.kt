package hun.portfolio.first.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel,
    navigateToChat: () -> Unit,
) {
    LoginScreen(
        onGoogleClick = { loginViewModel.googleLogin() },
        onKakaoTalkClick = { loginViewModel.kakaoTalkLogin() },
        onNaverClick = { loginViewModel.naverLogin() },
        onGuestClick = { loginViewModel.guestLogin() },
    ) {
        navigateToChat()
    }

    LaunchedEffect(key1 = true) {
        loginViewModel.uiEvent.collect { event ->
            when (event) {
                is LoginUiEvent.NavigateToChat -> {
                    navigateToChat()
                }

                is LoginUiEvent.ShowError -> {

                }
            }
        }
    }
}
