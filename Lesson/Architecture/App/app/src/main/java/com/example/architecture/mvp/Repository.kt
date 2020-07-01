package com.example.architecture.mvp

import com.example.architecture.Data

class Repository {
    fun getData() = Data.getData().shuffled()
}