package com.example.myapplication.main.domain.usecases

import com.example.myapplication.main.domain.Repository

class ExistUseCase (
    private val repository: Repository
)  {
    fun isUserExists():Boolean = repository.isUserExists()
}

