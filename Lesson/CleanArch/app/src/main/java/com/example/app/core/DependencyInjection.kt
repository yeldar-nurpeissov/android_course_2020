package com.example.app.core

import android.content.Context
import android.content.SharedPreferences
import com.example.app.feature.auth.data.AuthRepositoryImpl
import com.example.app.feature.auth.data.datasource.*
import com.example.app.feature.auth.domain.AuthRepository
import com.example.app.feature.auth.domain.AuthUseCase
import com.example.app.feature.auth.domain.GetTokenUseCase
import com.example.app.feature.auth.presentation.AuthViewModel

class DI {

    lateinit var sharedPreferences: SharedPreferences

    fun inject(context: Context) {
        sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
    }

    fun getAuthViewModel() = AuthViewModel(
        authUseCase = getAuthUseCase(),
        getTokenUseCase = getTokenUseCase()
    )

    private fun getAuthUseCase() = AuthUseCase(
        repository = repository
    )

    private fun getTokenUseCase() = GetTokenUseCase(
        repository = repository
    )

    private val repository: AuthRepository by lazy {
        AuthRepositoryImpl(
            authRemoteDataSource = getAuthRemoteDataSource(),
            authLocalDataSource = getAuthLocalDataSource()
        )
    }

    private fun getAuthRemoteDataSource(): AuthRemoteDataSource {
        return RestApiAuthRemoteDataSource(
            RestApi()
        )
    }

    private fun getAuthLocalDataSource(): AuthLocalDataSource {
        return SharedPrefLocalDataSource(sharedPreferences)
    }
}