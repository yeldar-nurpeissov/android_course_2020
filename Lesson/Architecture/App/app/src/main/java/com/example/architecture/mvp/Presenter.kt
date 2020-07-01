package com.example.architecture.mvp

interface Presenter {

    fun onButtonClicked()
    fun onListItemClicked(position: Int)
}