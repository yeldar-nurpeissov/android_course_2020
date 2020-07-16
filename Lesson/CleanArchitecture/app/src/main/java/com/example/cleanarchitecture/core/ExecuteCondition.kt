package com.example.cleanarchitecture.core

sealed class ExecuteCondition {
    object Success : ExecuteCondition()
    class Error(val err: Throwable) : ExecuteCondition()
}