package com.example.myapplication.main.domain

import com.example.myapplication.core.Resource
import com.example.myapplication.main.domain.entity.User

interface Repository {
    fun setName(username: String, surname: String): Resource<Unit>
    fun setDetail(dateOfBirth: String, weight: Int, height: Int ): Resource<Unit>
    fun setAbout(about: String): Resource<Unit>
    fun setUser(user: User):Resource<Unit>

    fun getMyUser(): User?
    fun isUserExists():Boolean
    fun deleteUser()
}