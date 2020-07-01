package com.example.architecture.mvi

import androidx.lifecycle.MutableLiveData
import com.example.architecture.Repository

class MainIntent(
    private val repository: Repository
) {

    val mainState = MutableLiveData<MainState>()

    init {
        mainState.value = MainState.ButtonState(false)
    }

    fun onAction(action: MainAction) {
        when (action) {
            is MainAction.ListItemClick -> onNameClicked(action.position)
            is MainAction.ButtonClick -> onButtonClicked()
        }
    }

    private fun onNameClicked(position: Int) {
        val name = repository.getNames()[position]
        mainState.value = MainState.ToastEvent(name)
    }

    private fun onButtonClicked() {
        mainState.value = MainState.ButtonState(true)
    }
}

sealed class MainAction {
    class ListItemClick(val position: Int) : MainAction()
    object ButtonClick : MainAction()
}

sealed class MainState {
    class ButtonState(val isClicked: Boolean) : MainState()
    class ToastEvent(val message: String) : MainState()
}

