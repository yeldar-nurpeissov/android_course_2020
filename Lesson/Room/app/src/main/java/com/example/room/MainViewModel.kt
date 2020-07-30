package com.example.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.room.db.entity.AccessLevel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    val users = repository.getAllUsers().asLiveData()

    fun onSaveUserClicked(name:String, age:Int, accessLevel: AccessLevel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveUser(name, age,  accessLevel)
        }
    }
}