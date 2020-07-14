package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.entity.User
import com.example.myapplication.domain.DeleteUseCase
import com.example.myapplication.domain.GetUseCase

class GetDetailViewModel(
    private val getUseCase: GetUseCase, private val deleteUseCase: DeleteUseCase
){
    val getUserEvent: LiveData<GetUserEvent> get() = _getUserEvent
    private val _getUserEvent = MutableLiveData<GetUserEvent>()

    val goEdit: LiveData<Boolean> get() = _goEdit
    private val _goEdit = MutableLiveData<Boolean>()

    val goRemove: LiveData<Boolean> get() = _goRemove
    private val _goRemove = MutableLiveData<Boolean>()


    init{
        getUser()
    }

    private fun getUser(){
        _getUserEvent.value = GetUserEvent(
            getUseCase.getUser()
        )
    }

    fun goToNext(){
        _goEdit.value = true
    }

    fun deleteUser(){
        deleteUseCase.deleteUser()
        _goRemove.value = true
    }

    fun cancelDel(){
        _goRemove.value = false
    }

    fun cancelMove(){
        _goEdit.value = false
    }
}

class GetUserEvent(val user: User?)
