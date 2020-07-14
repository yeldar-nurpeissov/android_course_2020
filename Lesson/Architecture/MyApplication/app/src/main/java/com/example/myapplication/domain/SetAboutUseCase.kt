package com.example.myapplication.domain

import com.example.myapplication.core.Resource

class SetAboutUseCase(
    private val repository: Repository
)  {
    fun setAbout(about:String):Resource<Unit>{
        return repository.setAbout(about)
    }
}