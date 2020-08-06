package com.example.room

import androidx.paging.DataSource
import androidx.paging.toLiveData
import com.example.room.db.UserItemKeyedDataSource
import com.example.room.db.dao.UserDao
import com.example.room.db.entity.AccessLevel
import com.example.room.db.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainRepository(
    private val userDao: UserDao,
    private val userItemKeyedDataSource: UserItemKeyedDataSource
) {

    val ddd = userDao.getAllUsersByPage().toLiveData(pageSize = 10)

    fun getItemKeyedDataSource() = userItemKeyedDataSource

//    fun getAllUsers(): Flow<List<User>> =
//        flow {
////            emit(userDao.getUserByLimit(0, 33))
//        }.flowOn(Dispatchers.IO)

    fun getUsersByPage(): DataSource.Factory<Int, User>  {
        return userDao.getAllUsersByPage()
    }

    suspend fun generateUsers() {
        withContext(Dispatchers.IO) {

            val count = userDao.getUsersCount()
            val totalCount = 10000
            if (count < totalCount) {
                for (i in 1..totalCount) {
                    val user = User(
                        name = "MyName $i",
                        accessLevel = AccessLevel.MODERATOR,
                        age = 50 % i
                    )
                    userDao.save(user)
                }
            }
        }
    }

    suspend fun saveUser(name: String, age: Int, accessLevel: AccessLevel) {
        val user = User(name = name, accessLevel = accessLevel, age = age)
        userDao.save(user)
    }
}