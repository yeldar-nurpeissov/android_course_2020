package com.example.solid.di

import com.example.solid.core.NetworkInfo
import com.example.solid.main.data.MainLocalDataSource
import com.example.solid.main.data.MainRemoteDataSource
import com.example.solid.main.data.MainRepositoryImpl
import com.example.solid.main.data.MainSharedPrefDataSource
import com.example.solid.main.domain.FormatUseCase
import com.example.solid.main.domain.MainRepository
import com.example.solid.main.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single<MainLocalDataSource> { MainSharedPrefDataSource() }
    single { MainRemoteDataSource() }
    single<NetworkInfo> {
        object : NetworkInfo {
            override fun isConnected(): Boolean = false
        }
    }
    single<MainRepository> {
        MainRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get(),
            networkInfo = get()
        )
    }

    factory {
        FormatUseCase(
            repository = get()
        )
    }

    viewModel {
        MainViewModel(
            formatUseCase = get()
        )
    }
}