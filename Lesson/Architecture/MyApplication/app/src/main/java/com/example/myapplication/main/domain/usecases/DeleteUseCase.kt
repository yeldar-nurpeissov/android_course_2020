package com.example.myapplication.main.domain.usecases

import com.example.myapplication.main.domain.Repository

class DeleteUseCase  (
    private val repository: Repository
)  {
    fun deleteUser()= repository.deleteUser()
}