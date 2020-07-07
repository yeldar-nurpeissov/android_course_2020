package com.example.solid.main.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.solid.main.domain.FormatUseCase

class ViewModel(
    private val formatUseCase: FormatUseCase
) {

    val liveData = MutableLiveData<String>()

    fun onButtonClicked() {
        liveData.value = formatUseCase()
    }
}