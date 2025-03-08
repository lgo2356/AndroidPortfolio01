package hun.portfolio.first.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import hun.portfolio.first.R
import kotlinx.coroutines.launch

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel,
    navigateToChat: () -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val onGoogleClick = {
        val credentialManager = CredentialManager.create(context)
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setAutoSelectEnabled(true)
            .setServerClientId(context.getString(R.string.default_web_client_id))
            .build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        coroutineScope.launch {
            runCatching {
                val response = credentialManager.getCredential(
                    context = context,
                    request = request,
                )

                loginViewModel.signInWithGoogle(response)
            }
        }
    }

    LoginScreen(
        onGoogleClick = { onGoogleClick() },
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
