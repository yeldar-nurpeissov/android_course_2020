package com.example.myapplication.main.domain.usecases

import com.example.myapplication.main.domain.entity.User
import com.example.myapplication.main.domain.Repository

class GetUseCase(
    private val repository: Repository
)  {
    fun getUser(): User? = repository.getMyUser()
}