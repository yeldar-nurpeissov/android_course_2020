package com.example.architecture.mvvm

import androidx.lifecycle.MutableLiveData
import com.example.architecture.Repository

class ViewModel(
    private val repository: Repository
) {

    val toastEvent = MutableLiveData<String>()
    val buttonClickEvent = MutableLiveData<Boolean>()

    init {
        buttonClickEvent.value = false
    }


    fun onListItemClicked(position: Int){
        val name = repository.getNames()[position]
        toastEvent.value = name
    }

    fun onButtonClicked() {
        buttonClickEvent.value = true
    }
}

