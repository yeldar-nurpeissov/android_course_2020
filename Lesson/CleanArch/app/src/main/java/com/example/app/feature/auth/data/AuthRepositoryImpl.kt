package com.example.app.feature.auth.data

import com.example.app.core.Resource
import com.example.app.feature.auth.data.datasource.AuthLocalDataSource
import com.example.app.feature.auth.data.datasource.AuthRemoteDataSource
import com.example.app.feature.auth.domain.AuthRepository

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {

    override fun authByPassword(username: String, password: String): Resource<Unit> {
        return try {
            val token = authRemoteDataSource.authByPassword(username, password)
            authLocalDataSource.saveToken(token)
            Resource.Success(Unit)
        } catch (throwable: Throwable) {
            Resource.Error(throwable)
        }
    }

    override fun getAccessToken(): String? {
        return authLocalDataSource.getToken()?.accessToken
    }
}