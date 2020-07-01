package com.example.architecture.mvp

import com.example.architecture.Data

class Presenter(private val view: Contract.View, private val repository: Repository): Contract.Presenter {
    override fun getData():List<String> = repository.getData()
}