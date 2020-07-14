package com.example.myapplication.domain

import com.example.myapplication.core.Resource

class SetDetailUseCase(
    private val repository: Repository
) {
    fun setDetail(dateOfBirth: String, weight: Int, height: Int ):Resource<Unit>{
        return repository.setDetail(dateOfBirth, weight, height)
    }
}