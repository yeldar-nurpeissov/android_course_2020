package com.example.cleanarchitecture.core

import android.content.Context
import android.content.SharedPreferences
import com.example.cleanarchitecture.feature.resume.data.ResumeRepositoryImpl
import com.example.cleanarchitecture.feature.resume.data.datasource.ResumeLocalDataSource
import com.example.cleanarchitecture.feature.resume.data.datasource.SharedPrefLocalDataSource
import com.example.cleanarchitecture.feature.resume.domain.ResumeRepository
import com.example.cleanarchitecture.feature.resume.domain.ResumeUseCase
import com.example.cleanarchitecture.feature.resume.domain.ShowUseCase
import com.example.cleanarchitecture.feature.resume.presentation.SlideViewModel

class DI {
    lateinit var sharedPreferences: SharedPreferences

    fun inject(context: Context) {
        sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
    }

    fun getShowUseCase() = ShowUseCase(
        repository = repository
    )

    fun getSaveUseCase() = ResumeUseCase(
        repository = repository
    )

    private val repository: ResumeRepository by lazy {
        ResumeRepositoryImpl(
            localDataSource = getResumeLocalDataSource()
        )
    }

    fun getResumeLocalDataSource(): ResumeLocalDataSource =
        SharedPrefLocalDataSource(sharedPreferences)
}