package com.example.app.feature.auth.domain

interface AuthRepository {
    fun isFillness(firstName: String, secondName: String, date: String, height: String, width: String, about: String): Boolean
}