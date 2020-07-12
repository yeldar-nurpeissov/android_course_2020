package com.example.app.feature.auth.data.datasource

import android.content.SharedPreferences
import com.example.app.feature.auth.data.entity.Token

interface AuthLocalDataSource {
    fun saveToken(token: Token)
    fun getToken(): Token?
}

class CacheLocalDataSource : AuthLocalDataSource {
    private var token: Token? = null

    override fun saveToken(token: Token) {
        this.token = token
    }

    override fun getToken(): Token? = token
}

class SharedPrefLocalDataSource(
    private val sharedPreferences: SharedPreferences
):AuthLocalDataSource {
    private val KEY_TOKEN = "key_token"
    private val separator = "#####"

    override fun saveToken(token: Token) {
        val tokenString = "${token.accessToken}$separator${token.refreshToken}"
        sharedPreferences.edit().
            putString(KEY_TOKEN, tokenString)
            .apply()

    }

    override fun getToken(): Token? {
        val tokenString = sharedPreferences.getString(KEY_TOKEN, null)

        if (tokenString?.contains(separator) == true) {
            val tokenParts = tokenString.split(separator)
            val accessToken = tokenParts[0]
            val refreshToken = tokenParts[1]

            return Token(accessToken, refreshToken)
        }

        return null
    }

}