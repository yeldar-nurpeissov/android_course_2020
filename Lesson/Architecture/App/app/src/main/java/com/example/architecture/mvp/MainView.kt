package com.example.architecture.mvp

interface MainView {
    fun showToast(message: String)
    fun onButtonClicked()
}