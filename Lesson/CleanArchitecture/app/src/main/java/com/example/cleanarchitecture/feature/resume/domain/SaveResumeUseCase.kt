package com.example.cleanarchitecture.feature.resume.domain

import com.example.cleanarchitecture.core.ExecuteCondition
import com.example.cleanarchitecture.feature.resume.data.entity.Resume

class SaveResumeUseCase(
private val repository: ResumeRepository
) {
    operator fun invoke(resume: Resume): ExecuteCondition {
        return repository.saveResume(resume)
    }
}