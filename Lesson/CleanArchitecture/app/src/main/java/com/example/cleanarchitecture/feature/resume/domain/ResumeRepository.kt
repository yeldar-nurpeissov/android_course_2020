package com.example.cleanarchitecture.feature.resume.domain

import com.example.cleanarchitecture.core.ExecuteCondition
import com.example.cleanarchitecture.feature.resume.data.entity.Resume

interface ResumeRepository {
    fun getResume() : Resume?
    fun saveResume(resume: Resume): ExecuteCondition
    fun deleteResume(): ExecuteCondition
}