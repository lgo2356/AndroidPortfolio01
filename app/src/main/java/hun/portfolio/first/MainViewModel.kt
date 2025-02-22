package hun.portfolio.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _text = MutableLiveData<String>()

    val text: LiveData<String> get() = _text

    init {
        _text.value = "Hello"
    }

    fun change() {
        _text.value = "World!"
    }
}
