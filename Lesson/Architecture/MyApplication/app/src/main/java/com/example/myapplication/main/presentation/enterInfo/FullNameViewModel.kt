package com.example.myapplication.main.presentation.enterInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.core.Resource
import com.example.myapplication.main.domain.entity.User
import com.example.myapplication.main.domain.usecases.ExistUseCase
import com.example.myapplication.main.domain.usecases.GetUseCase
import com.example.myapplication.main.domain.usecases.SetNameUseCase

class FullNameViewModel(private val usecase: SetNameUseCase, private val existUseCase: ExistUseCase,
                    private val getUseCase: GetUseCase
): ViewModel() {
    val authState: LiveData<SetUIState> get() = _authState
    private val _authState = MutableLiveData<SetUIState>()

    init{
        if (existUseCase.isUserExists()){
            getUser()
        }
    }

    fun getUser(){
        val user = getUseCase.getUser()
        _authState.value =
            SetUIState.UserEdit(
                user!!
            )
    }

    fun onNextButtonClicked(
        name: String?,
        surname: String?
    ){
        if (name.isNullOrBlank()){
            _authState.value =
                SetUIState.ValidationError(
                    "Fill name"
                )
            return
        }

        if (surname.isNullOrBlank()){
            _authState.value =
                SetUIState.ValidationError(
                    "Fill surname"
                )
            return
        }

        when (val resource  = usecase.setName(name,surname)){
            is Resource.Success ->
                _authState.value =
                    SetUIState.Success
            is Resource.Error ->
                _authState.value =
                    SetUIState.Error(
                        resource.throwable
                    )
        }
    }
}

sealed class SetUIState{
    class Error(val throwable: Throwable):
        SetUIState()
    class ValidationError(val message: String): SetUIState()
    object Success: SetUIState()
    class UserEdit(val user: User):
        SetUIState()
}