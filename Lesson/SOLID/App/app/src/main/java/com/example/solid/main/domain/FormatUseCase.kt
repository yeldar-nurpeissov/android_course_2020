package com.example.solid.main.domain

class FormatUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(): String {
        return repository.format()
    }
}