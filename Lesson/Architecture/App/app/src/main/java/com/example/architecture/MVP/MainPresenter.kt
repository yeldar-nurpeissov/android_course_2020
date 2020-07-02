package com.example.architecture.MVP

interface MainPresenter {
    fun onItemClicked(itemText: String)
    fun onButtonClicked()
    fun onDataLoaded()
}

interface Shuffle{
    fun shuffle() : List<String>
}