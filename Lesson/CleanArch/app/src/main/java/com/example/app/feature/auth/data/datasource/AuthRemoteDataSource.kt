package com.example.app.feature.auth.data.datasource

import com.example.app.feature.auth.data.entity.Token

interface AuthRemoteDataSource {
    fun authByPassword(username: String, password: String): Token
}

class RestApiAuthRemoteDataSource(
    private val restApi: RestApi
) : AuthRemoteDataSource {

    override fun authByPassword(username: String, password: String): Token {
        return restApi.authByPassword(username, password)
    }
}

class RestApi {

    fun authByPassword(username: String, password: String): Token {
        return Token(
            accessToken = "$username:$password",
            refreshToken = "wegwegweg3434g34g34"
        )
    }
}