package com.example.myapplication.domain

import com.example.myapplication.core.Resource

class SetNameUseCase(
    private val repository: Repository
) {
    fun setName(name: String, surname: String): Resource<Unit>{
        return repository.setName(name, surname)
    }
}