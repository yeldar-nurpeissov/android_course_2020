package com.example.myapplication.main.domain.usecases

import com.example.myapplication.core.Resource
import com.example.myapplication.main.domain.Repository

class SetNameUseCase(
    private val repository: Repository
) {
    fun setName(name: String, surname: String): Resource<Unit>{
        return repository.setName(name, surname)
    }
}