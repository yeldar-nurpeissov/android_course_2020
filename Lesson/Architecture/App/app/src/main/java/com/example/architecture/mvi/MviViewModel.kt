package com.example.architecture.mvi

import androidx.lifecycle.MutableLiveData
import com.example.architecture.Repository

class MviViewModel(
    val repo: Repository
) {
    val mainState = MutableLiveData<MviState>()

    init {
        onGetItems()
    }

    fun onAction(action: MviAction) {
        when(action){
            is MviAction.BtnClick -> onBtnClicked()
            is MviAction.ItemClick -> onItemClicked(action.text)
            is MviAction.GetItems -> onGetItems()
        }
    }

    fun onItemClicked(text: String){
        mainState.value = MviState.ToastEvent(text)
    }

    fun onBtnClicked() {
        mainState.value = MviState.NextActivity
    }

    fun onGetItems(){
        mainState.value = MviState.GetItemsSuccess(repo.getDataList())
    }
}

sealed class MviAction {
    object BtnClick : MviAction()
    class ItemClick(val text: String) : MviAction()
    object GetItems : MviAction()
}

sealed class MviState {
    class ToastEvent(val text: String): MviState()
    object NextActivity: MviState()
    class GetItemsSuccess(val items: ArrayList<String>): MviState()
}