package com.example.architecture.MVI

import androidx.lifecycle.MutableLiveData
import com.example.architecture.Repository.Repository

class MainViewModel(private val repository: Repository) {
    val mainState = MutableLiveData<MainState>()

    init {
        onGetItems()
    }

    fun onAction(action: MainAction){
        when(action){
            is MainAction.GetItems -> onGetItems()
            is MainAction.ItemClicked -> onItemClicked(action.text)
            is MainAction.NextBtnClicked -> onNextBtnActivityClicked()
        }
    }

    fun onGetItems(){
        mainState.value = MainState.LoadingData(repository.getCities())
    }

    fun onItemClicked(text: String){
        mainState.value = MainState.ToastEvent(text)
    }

    fun onNextBtnActivityClicked(){
        mainState.value = MainState.NextActivity(true)
    }

}


sealed class MainAction(){
    class GetItems(): MainAction()
    class ItemClicked(val text: String): MainAction()
    class NextBtnClicked(): MainAction()
}
sealed class MainState(){
    class LoadingData(val items: List<String>): MainState()
    class NextActivity(val nextActivity: Boolean): MainState()
    class ToastEvent(val text: String): MainState()
}