package com.example.cleanarchitecture.feature.resume.domain

import com.example.cleanarchitecture.core.ExecuteCondition

class DeleteResumeUseCase(
    private val repository: ResumeRepository
) {
    operator fun invoke(): ExecuteCondition {
        return repository.deleteResume()
    }
}