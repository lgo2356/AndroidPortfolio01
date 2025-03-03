package hun.portfolio.first.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hun.portfolio.first.data.user.UserEntity
import hun.portfolio.first.data.user.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class LoginUiEvent {
    data object NavigateToChat : LoginUiEvent()
    data class ShowError(val message: String) : LoginUiEvent()
}

class LoginViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _uiEvent = Channel<LoginUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun googleLogin() {

    }

    fun kakaoTalkLogin() {

    }

    fun naverLogin() {

    }

    fun guestLogin() {
        viewModelScope.launch {
            val id = "guest_id"
            val name = "guest_name"

            if (userRepository.isExist(id)) {
                _uiEvent.send(LoginUiEvent.NavigateToChat)
            } else {
                if (userRepository.addUser(id, name)) {
                    _uiEvent.send(LoginUiEvent.NavigateToChat)
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
