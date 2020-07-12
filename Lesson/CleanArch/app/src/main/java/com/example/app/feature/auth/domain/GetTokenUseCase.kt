package com.example.app.feature.auth.domain

class GetTokenUseCase(
    private val repository: AuthRepository
) {
    fun execute(): String? {
        return repository.getAccessToken()
    }
}