package com.example.architecture.MVVM

import androidx.lifecycle.MutableLiveData
import com.example.architecture.Repository.Repository

class MainViewModel (private val repository: Repository){
    val toastEvent = MutableLiveData<String>()
    val nextActivityEvent = MutableLiveData<Boolean>()
    val items = MutableLiveData<List<String>>()

    init {
        items.value = repository.getCities()
        nextActivityEvent.value = false
    }

    fun onItemClicked(text: String){
        toastEvent.value = text
    }

    fun onNextBtnClicked(){
        nextActivityEvent.value = true
    }
}