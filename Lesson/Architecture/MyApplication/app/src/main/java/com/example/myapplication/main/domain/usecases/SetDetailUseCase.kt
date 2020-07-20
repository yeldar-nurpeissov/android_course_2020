package com.example.myapplication.main.domain.usecases

import com.example.myapplication.core.Resource
import com.example.myapplication.main.domain.Repository

class SetDetailUseCase(
    private val repository: Repository
) {
    fun setDetail(dateOfBirth: String, weight: Int, height: Int ):Resource<Unit>{
        return repository.setDetail(dateOfBirth, weight, height)
    }
}