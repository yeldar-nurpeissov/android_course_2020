package com.example.architecture.repo

class Repository {
    fun getDataList(): ArrayList<String> {
        return arrayListOf(
            "Fire", "Water", "Earth", "Air"
        )
    }
}