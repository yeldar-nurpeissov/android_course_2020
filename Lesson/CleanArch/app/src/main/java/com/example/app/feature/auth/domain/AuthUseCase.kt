package com.example.app.feature.auth.domain

import com.example.app.core.Resource

class AuthUseCase(
    private val repository: AuthRepository
) {
    fun execute(username: String, password: String): Resource<Unit> {
        return repository.authByPassword(username, password)
    }
}