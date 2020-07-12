package com.example.cleanarchitecture.feature.resume.domain

import com.example.cleanarchitecture.core.ExecuteCondition
import com.example.cleanarchitecture.feature.resume.data.entity.Resume

class ShowUseCase(
    private val repository: ResumeRepository
) {
    fun delete(): ExecuteCondition {
        return repository.deleteResume()
    }

    fun retrieve(): Resume? {
        return repository.getResume()
    }
}