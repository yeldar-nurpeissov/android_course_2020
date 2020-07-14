package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.core.Resource
import com.example.myapplication.data.entity.User
import com.example.myapplication.domain.ExistUseCase
import com.example.myapplication.domain.GetUseCase
import com.example.myapplication.domain.SetNameUseCase

class NameViewModel(private val usecase: SetNameUseCase, private val existUseCase: ExistUseCase,
private val getUseCase: GetUseCase) {
    val authState: LiveData<SetUIState> get() = _authState
    private val _authState = MutableLiveData<SetUIState>()

    init{
        if (existUseCase.isUserExists()){
            getUser()
        }
    }

    fun getUser(){
        val user = getUseCase.getUser()
        _authState.value = SetUIState.UserEdit(user!!)
    }

    fun onNextButtonClicked(
        name: String?,
        surname: String?
    ){
        if (name.isNullOrBlank()){
            _authState.value = SetUIState.ValidationError("Fill name")
            return
        }

        if (surname.isNullOrBlank()){
            _authState.value = SetUIState.ValidationError("Fill surname")
            return
        }

        when (val resource  = usecase.setName(name,surname)){
            is Resource.Success ->
                _authState.value = SetUIState.Success
            is Resource.Error ->
                _authState.value = SetUIState.Error(resource.throwable)
        }
    }
}

sealed class SetUIState{
    class Error(val throwable: Throwable):SetUIState()
    class ValidationError(val message: String): SetUIState()
    object Success: SetUIState()
    class UserEdit(val user: User):SetUIState()
}