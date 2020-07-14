package com.example.myapplication.domain

import com.example.myapplication.data.entity.User

class ExistUseCase (
    private val repository: Repository
)  {
    fun isUserExists():Boolean = repository.isUserExists()
}

