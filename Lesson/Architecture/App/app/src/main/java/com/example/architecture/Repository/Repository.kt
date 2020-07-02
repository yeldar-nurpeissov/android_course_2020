package com.example.architecture.Repository

import com.example.architecture.MVP.Shuffle

class Repository {
    private val dataClass: DataClass = DataClass()

    fun getCities(): List<String> {
        return dataClass.shuffle()
    }
}

class DataClass: Shuffle {
    private var list = listOf("Astana","Almaty","Shymkent","Aktobe","Atyrau","London","NewYork","Maiami","Kokshetau","Kostanay","Pavladar")
    override fun shuffle(): List<String> {
        return list.shuffled()
    }
}