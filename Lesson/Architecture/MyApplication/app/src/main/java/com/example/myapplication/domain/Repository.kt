package com.example.myapplication.domain

import com.example.myapplication.core.Resource
import com.example.myapplication.data.entity.User

interface Repository {
    fun setName(username: String, surname: String): Resource<Unit>
    fun setDetail(dateOfBirth: String, weight: Int, height: Int ): Resource<Unit>
    fun setAbout(about: String): Resource<Unit>

    fun getMyUser(): User?
    fun isUserExists():Boolean
    fun deleteUser()
}