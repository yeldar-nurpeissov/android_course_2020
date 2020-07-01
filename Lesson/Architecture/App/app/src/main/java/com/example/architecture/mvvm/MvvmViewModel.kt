package com.example.architecture.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecture.mvp.Repository

class MvvmViewModel(private val repository: Repository): ViewModel() {
    val data = MutableLiveData<List<String>>()

    fun getData(){
        data.value = repository.getData()
    }
}