package com.example.myapplication.domain

import com.example.myapplication.data.entity.User

class GetUseCase(
    private val repository: Repository
)  {
    fun getUser(): User? = repository.getMyUser()
}