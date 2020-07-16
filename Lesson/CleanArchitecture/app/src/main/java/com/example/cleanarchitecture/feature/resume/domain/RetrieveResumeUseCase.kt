package com.example.cleanarchitecture.feature.resume.domain

import com.example.cleanarchitecture.core.ExecuteCondition
import com.example.cleanarchitecture.feature.resume.data.entity.Resume

class RetrieveResumeUseCase(
    private val repository: ResumeRepository
) {

    operator fun invoke(): Resume? {
        return repository.getResume()
    }
}