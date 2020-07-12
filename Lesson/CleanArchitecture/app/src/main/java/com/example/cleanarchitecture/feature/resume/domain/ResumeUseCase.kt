package com.example.cleanarchitecture.feature.resume.domain

import com.example.cleanarchitecture.core.ExecuteCondition
import com.example.cleanarchitecture.feature.resume.data.entity.Resume

class ResumeUseCase(
private val repository: ResumeRepository
) {
    fun save(resume: Resume): ExecuteCondition {
        return repository.saveResume(resume)
    }

    fun retrieve(): Resume? {
        return repository.getResume()
    }
}