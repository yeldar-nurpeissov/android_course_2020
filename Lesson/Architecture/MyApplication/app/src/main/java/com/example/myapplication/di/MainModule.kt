package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.main.data.RepositoryImpl
import com.example.myapplication.main.data.datasource.LocalDataSource
import com.example.myapplication.main.data.datasource.SharedPrefLocalDataSource
import com.example.myapplication.main.domain.Repository
import com.example.myapplication.main.domain.usecases.*
import com.example.myapplication.main.presentation.detailOfUser.GetDetailViewModel
import com.example.myapplication.main.presentation.enterInfo.AboutViewModel
import com.example.myapplication.main.presentation.enterInfo.DetailViewModel
import com.example.myapplication.main.presentation.enterInfo.FillInViewModel
import com.example.myapplication.main.presentation.enterInfo.FullNameViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single {
        androidContext().getSharedPreferences("test", Context.MODE_PRIVATE)
    }
    single<LocalDataSource> {
        SharedPrefLocalDataSource(get())
    }
    single<Repository> {
        RepositoryImpl(get())
    }
    single {
        DeleteUseCase(get())
    }
    single {
        ExistUseCase(get())
    }
    single {
        GetUseCase(get())
    }
    single {
        SetAboutUseCase(get())
    }
    single {
        SetDetailUseCase(get())
    }
    single {
        SetNameUseCase(get())
    }
    viewModel {
        GetDetailViewModel(get(),get())
    }
    viewModel {
        AboutViewModel(get(),get(),get())
    }
    viewModel {
        DetailViewModel(get(),get(),get())
    }
    viewModel {
        FullNameViewModel(get(),get(),get())
    }
    viewModel {
        FillInViewModel(get())
    }
}