package com.example.architecture

import androidx.lifecycle.MutableLiveData

class MainViewModel(
    private val repository: Repository
) {

    val mainState = MutableLiveData<MainState>()

    init {
        mainState.value = MainState.Loading(isLoading = true)
    }

    fun onAction(action: MainAction) {
        when (action) {
            is MainAction.TextChanged -> onTextChanged(action.text)
            is MainAction.ButtonClick -> onButtonClicked()
        }
    }

    private fun onTextChanged(text: String) {
        repository.onTextChanged(text)
    }

    private fun onButtonClicked() {
        val result = repository.getFormattedText()

        mainState.value = MainState.Loading(false)
        mainState.value = MainState.ToastEvent(result)
    }
}

sealed class MainAction {
    class TextChanged(val text: String) : MainAction()
    object ButtonClick : MainAction()
}

sealed class MainState {
    class Loading(val isLoading: Boolean) : MainState()
    class ToastEvent(val message: String) : MainState()
}