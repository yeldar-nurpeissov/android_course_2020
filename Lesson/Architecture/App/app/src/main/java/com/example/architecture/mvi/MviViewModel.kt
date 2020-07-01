package com.example.architecture.mvi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecture.mvp.Repository

class MviViewModel(private val repository: Repository): ViewModel() {

    val data = MutableLiveData<MainState>()

    fun onAction(action: MainAction){
        when (action){
            is MainAction.GetData -> getData()
        }
    }

    private fun getData(){
        data.value = MainState.GetData(repository.getData())
    }
}

sealed class MainAction{
    object GetData : MainAction()
}

sealed class MainState{
    class GetData(val list: List<String>): MainState()
}