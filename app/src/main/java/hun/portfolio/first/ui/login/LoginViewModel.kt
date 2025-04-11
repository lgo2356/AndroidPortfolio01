package hun.portfolio.first.ui.login

import android.util.Log
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import hun.portfolio.first.data.user.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class LoginUiEvent {
    data object NavigateToChatList : LoginUiEvent()
    data class ShowError(val message: String) : LoginUiEvent()
}

class LoginViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _uiEvent = Channel<LoginUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        Firebase.auth.currentUser?.let {
            viewModelScope.launch { _uiEvent.send(LoginUiEvent.NavigateToChatList) }
        }
    }

    fun signInWithGoogle(response: GetCredentialResponse) {
        when (val credential = response.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    runCatching {
                        val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                        val googleCredential = GoogleAuthProvider
                            .getCredential(tokenCredential.idToken, null)

                        Firebase.auth.signInWithCredential(googleCredential)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    viewModelScope.launch { _uiEvent.send(LoginUiEvent.NavigateToChatList) }
                                } else {
                                    Log.d("TAG", "Failure")
                                }
                            }
                    }
                }
            }
        }
    }

    fun guestLogin() {
        viewModelScope.launch {
            val id = "guest_id"
            val name = "guest_name"
            val profileImagePath = "guest_profile"

            if (userRepository.isExist(id)) {
                _uiEvent.send(LoginUiEvent.NavigateToChatList)
            } else {
                if (userRepository.addUser(id, name)) {
                    _uiEvent.send(LoginUiEvent.NavigateToChatList)
                } else {
                    _uiEvent.send(LoginUiEvent.ShowError("error"))
                }
            }
        }
    }

    companion object {
        fun provideFactory(
            userRepository: UserRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return LoginViewModel(userRepository = userRepository) as T
            }
        }
    }
}
