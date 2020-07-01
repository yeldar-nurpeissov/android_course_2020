package com.example.architecture.mvvm

import androidx.lifecycle.MutableLiveData
import com.example.architecture.repo.Repository

class MvvmViewModel( repo : Repository) {
    val repoItems = MutableLiveData<ArrayList<String>>()
    val nextActivityEvent = MutableLiveData<Boolean>()
    val toastEvent = MutableLiveData<String>()

    init {
        repoItems.value = repo.getDataList()
        nextActivityEvent.value = false
    }

    fun onItemClicked(text: String){
        toastEvent.value = text
    }

    fun onBtnClicked() {
        nextActivityEvent.value = true
    }
}