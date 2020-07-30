package com.example.room

import com.example.room.db.dao.UserDao
import com.example.room.db.entity.AccessLevel
import com.example.room.db.entity.User
import kotlinx.coroutines.flow.Flow

class MainRepository(
    private val userDao: UserDao
) {

    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

    suspend fun saveUser(name:String, age:Int, accessLevel: AccessLevel) {
        val user = User(name = name, accessLevel = accessLevel, age = age)
        userDao.save(user)
    }
}