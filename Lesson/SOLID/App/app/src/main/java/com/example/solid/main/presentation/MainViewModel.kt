package com.example.solid.main.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.solid.main.domain.FormatUseCase

class MainViewModel(
    private val formatUseCase: FormatUseCase,
//    Just for learning, not real use
    isEditing:Boolean
) : ViewModel() {

    val liveData = MutableLiveData<String>()

    fun onButtonClicked() {
        liveData.value = formatUseCase()
    }
}