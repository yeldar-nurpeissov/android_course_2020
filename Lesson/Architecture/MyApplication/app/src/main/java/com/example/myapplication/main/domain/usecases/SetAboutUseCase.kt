package com.example.myapplication.main.domain.usecases

import com.example.myapplication.core.Resource
import com.example.myapplication.main.domain.Repository

class SetAboutUseCase(
    private val repository: Repository
)  {
    fun setAbout(about:String):Resource<Unit>{
        return repository.setAbout(about)
    }
}