package com.example.architecture

import androidx.lifecycle.MutableLiveData

class MainViewModel(
    private val repository: Repository
) {

    val toastEvent = MutableLiveData<String>()
    val loadingState = MutableLiveData<Boolean>()

    init {
        loadingState.value = true
    }

    fun onTextChanged(text: String) {
        repository.onTextChanged(text)
    }

    fun onButtonClicked() {
        val result = repository.getFormattedText()

        loadingState.value = false
        toastEvent.value = result
    }
}