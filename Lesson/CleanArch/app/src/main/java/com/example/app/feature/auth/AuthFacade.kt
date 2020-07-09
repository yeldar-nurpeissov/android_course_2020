package com.example.app.feature.auth

import com.example.app.feature.auth.domain.AuthUseCase

interface AuthFacade {
    fun authByPassword(username:String, password:String)
}

class DefaultAuthFacade(
    val authUseCase: AuthUseCase
):AuthFacade {

    override fun authByPassword(username: String, password: String) {
        authUseCase.execute(username, password)
    }
}