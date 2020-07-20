package com.example.myapplication.main.presentation.detailOfUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.main.domain.entity.User
import com.example.myapplication.main.domain.usecases.DeleteUseCase
import com.example.myapplication.main.domain.usecases.GetUseCase

class GetDetailViewModel(
    private val getUseCase: GetUseCase, private val deleteUseCase: DeleteUseCase
): ViewModel(){
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
