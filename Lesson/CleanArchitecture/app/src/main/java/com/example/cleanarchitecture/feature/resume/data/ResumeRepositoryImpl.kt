package com.example.cleanarchitecture.feature.resume.data

import com.example.cleanarchitecture.core.ExecuteCondition
import com.example.cleanarchitecture.feature.resume.data.datasource.ResumeLocalDataSource
import com.example.cleanarchitecture.feature.resume.data.entity.Resume
import com.example.cleanarchitecture.feature.resume.domain.ResumeRepository

class ResumeRepositoryImpl(
    private val localDataSource: ResumeLocalDataSource
): ResumeRepository {
    override fun getResume(): Resume? {
        return localDataSource.getResume()
    }

    override fun saveResume(resume: Resume): ExecuteCondition {
        return try {
            localDataSource.saveResume(resume)
            ExecuteCondition.Success
        } catch (throwable: Throwable){
            ExecuteCondition.Error(throwable)
        }
    }

    override fun deleteResume(): ExecuteCondition {
        return try {
            localDataSource.deleteResume()
            ExecuteCondition.Success
        } catch (throwable: Throwable){
            ExecuteCondition.Error(throwable)
        }
    }
}