package com.example.myapplication.main.presentation.enterInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.core.Resource
import com.example.myapplication.main.domain.usecases.ExistUseCase
import com.example.myapplication.main.domain.usecases.GetUseCase
import com.example.myapplication.main.domain.usecases.SetDetailUseCase

class DetailViewModel(private val setDetailUseCase: SetDetailUseCase,
                      existUseCase: ExistUseCase, private val getUseCase: GetUseCase
) : ViewModel(){

    val authState: LiveData<SetUIState> get() = _authState
    private val _authState = MutableLiveData<SetUIState>()

    init{
        if (existUseCase.isUserExists()){
            getUser()
        }
    }

    private fun getUser(){
        val user = getUseCase.getUser()
        _authState.value =
            SetUIState.UserEdit(
                user!!
            )
    }

    fun onNextButtonClicked(
        dateOfBirth: String?,
        weight: String?,
        height: String?
    ){
        if (dateOfBirth.isNullOrBlank()){
            _authState.value =
                SetUIState.ValidationError(
                    "Fill date of birth"
                )
            return
        }

        if (weight.isNullOrBlank() || weight.toInt() < 0){
            _authState.value =
                SetUIState.ValidationError(
                    "Fill valid weight"
                )
            return
        }

        if (height.isNullOrBlank() || height.toInt() < 0){
            _authState.value =
                SetUIState.ValidationError(
                    "Fill valid height"
                )
            return
        }

        when (val resource  = setDetailUseCase.setDetail(dateOfBirth, weight.toInt(), height.toInt())){
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