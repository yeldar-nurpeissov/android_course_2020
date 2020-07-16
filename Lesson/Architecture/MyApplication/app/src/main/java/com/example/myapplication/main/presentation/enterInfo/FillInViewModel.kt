package com.example.myapplication.main.presentation.enterInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.main.domain.usecases.ExistUseCase

class FillInViewModel(private val existUseCase: ExistUseCase): ViewModel(){
    val getExistEvent: LiveData<UserExistsEvent> get() = _getExistEvent
    private val _getExistEvent = MutableLiveData<UserExistsEvent>()

    init {
        isUserExist()
    }

    private fun isUserExist(){
        _getExistEvent.value =
            UserExistsEvent(
                existUseCase.isUserExists()
            )
    }

}

class UserExistsEvent(val boolean: Boolean)