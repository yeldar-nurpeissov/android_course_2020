package com.example.cleanarchitecture.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.cleanarchitecture.feature.resume.data.ResumeRepositoryImpl
import com.example.cleanarchitecture.feature.resume.data.datasource.ResumeLocalDataSource
import com.example.cleanarchitecture.feature.resume.data.datasource.SharedPrefLocalDataSource
import com.example.cleanarchitecture.feature.resume.domain.DeleteResumeUseCase
import com.example.cleanarchitecture.feature.resume.domain.ResumeRepository
import com.example.cleanarchitecture.feature.resume.domain.SaveResumeUseCase
import com.example.cleanarchitecture.feature.resume.domain.RetrieveResumeUseCase
import com.example.cleanarchitecture.feature.resume.presentation.profile.ShowViewModel
import com.example.cleanarchitecture.feature.resume.presentation.edit.SlideViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var mainModule = module {

    single {
        provideSecurePreferences(
            androidApplication()
        )
    }

    single<ResumeLocalDataSource>{SharedPrefLocalDataSource(
        sharedPref = get()
    )}
    single<ResumeRepository>{ResumeRepositoryImpl(
        localDataSource = get()
    )}
    factory {
        RetrieveResumeUseCase(
            repository = get()
        )
    }
    factory {
        SaveResumeUseCase(
            repository = get()
        )
    }
    factory {
        DeleteResumeUseCase(
            repository = get()
        )
    }

    viewModel{(isEditing: Boolean) ->
        SlideViewModel(
            retrieveResumeUseCase = get(),
            saveResumeUseCase = get(),
            isEditing = isEditing
        )
    }

    viewModel {
        ShowViewModel(
            retrieveResumeUseCase = get(),
            deleteResumeUseCase = get()
        )
    }
}

private fun provideSecurePreferences(app: Application): SharedPreferences =
    app.getSharedPreferences("test", Context.MODE_PRIVATE)
