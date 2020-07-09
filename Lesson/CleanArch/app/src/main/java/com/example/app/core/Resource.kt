package com.example.app.core

sealed class Resource<T> {
    class Error(val throwable: Throwable) : Resource<Unit>()
    class Success<T>(val data: T) : Resource<T>()
}