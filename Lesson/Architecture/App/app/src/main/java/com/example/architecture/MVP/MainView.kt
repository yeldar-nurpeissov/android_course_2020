package com.example.architecture.MVP

interface MainView {
    fun showToast(text: String)
    fun setAdapter(list: List<String>)
    fun nextActivity()
}