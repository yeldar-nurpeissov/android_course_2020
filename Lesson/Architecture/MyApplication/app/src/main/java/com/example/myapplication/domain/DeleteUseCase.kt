package com.example.myapplication.domain

class DeleteUseCase  (
    private val repository: Repository
)  {
    fun deleteUser()= repository.deleteUser()
}