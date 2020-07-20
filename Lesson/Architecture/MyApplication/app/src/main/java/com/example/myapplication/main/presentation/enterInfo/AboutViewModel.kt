package com.example.myapplication.main.presentation.enterInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.core.Resource
import com.example.myapplication.main.domain.usecases.ExistUseCase
import com.example.myapplication.main.domain.usecases.GetUseCase
import com.example.myapplication.main.domain.usecases.SetAboutUseCase

class AboutViewModel(private val usecase: SetAboutUseCase,
                     existUseCase: ExistUseCase, private val getUseCase: GetUseCase
) :ViewModel(){
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
        about: String?
    ){
        if (about.isNullOrBlank()){
            _authState.value =
                SetUIState.ValidationError(
                    "Fill about yourself"
                )
            return
        }

        when (val resource  = usecase.setAbout(about)){
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

