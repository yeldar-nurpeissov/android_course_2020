package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.core.Resource
import com.example.myapplication.data.entity.User
import com.example.myapplication.domain.ExistUseCase
import com.example.myapplication.domain.GetUseCase
import com.example.myapplication.domain.SetAboutUseCase

class AboutViewModel(private val usecase: SetAboutUseCase,
                     private val existUseCase: ExistUseCase, private val getUseCase: GetUseCase
) {
    val authState: LiveData<SetUIState> get() = _authState
    private val _authState = MutableLiveData<SetUIState>()

    init{
        if (existUseCase.isUserExists()){
            getUser()
        }
    }

    private fun getUser(){
        val user = getUseCase.getUser()
        _authState.value = SetUIState.UserEdit(user!!)
    }

    fun onNextButtonClicked(
        about: String?
    ){
        if (about.isNullOrBlank()){
            _authState.value = SetUIState.ValidationError("Fill about yourself")
            return
        }

        when (val resource  = usecase.setAbout(about)){
            is Resource.Success ->
                _authState.value = SetUIState.Success
            is Resource.Error ->
                _authState.value = SetUIState.Error(resource.throwable)
        }
    }
}

