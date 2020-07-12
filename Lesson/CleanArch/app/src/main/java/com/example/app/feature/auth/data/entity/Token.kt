package com.example.app.feature.auth.data.entity

data class Token(
    val accessToken: String,
    val refreshToken: String
)