package hun.portfolio.first.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class MainUiState(
    val topBarText: String
)

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState("홈"))
    val uiState: StateFlow<MainUiState> = _uiState

    fun onSelectedBottomBarItem(index: Int) {
        when (index) {
            0 -> {
                setTopBarText("홈")
            }

            1 -> {
                setTopBarText("채팅 리스트")
            }

            else -> {

            }
        }
    }

    private fun setTopBarText(text: String) {
        _uiState.update { MainUiState(topBarText = text) }
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel() as T
            }
        }
    }
}
