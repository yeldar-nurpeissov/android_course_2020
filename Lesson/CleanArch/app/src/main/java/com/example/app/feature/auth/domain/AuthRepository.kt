package com.example.app.feature.auth.domain

import com.example.app.core.Resource

interface AuthRepository {
    fun authByPassword(username: String, password: String): Resource<Unit>
    fun getAccessToken(): String?
}