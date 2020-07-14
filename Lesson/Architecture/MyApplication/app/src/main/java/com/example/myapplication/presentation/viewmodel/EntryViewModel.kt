package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.domain.ExistUseCase

class EntryViewModel(private val existUseCase: ExistUseCase){
    val getExistEvent: LiveData<UserExistsEvent> get() = _getExistEvent
    private val _getExistEvent = MutableLiveData<UserExistsEvent>()

    init {
        isUserExist()
    }

    fun isUserExist(){
        _getExistEvent.value = UserExistsEvent(
        existUseCase.isUserExists()
        )
    }

}

class UserExistsEvent(val boolean: Boolean)