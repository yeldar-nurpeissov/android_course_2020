package com.example.myapplication.main.data

import android.util.Log
import com.example.myapplication.core.Resource
import com.example.myapplication.main.data.datasource.LocalDataSource
import com.example.myapplication.main.domain.entity.User
import com.example.myapplication.main.domain.Repository

class RepositoryImpl(
    private val localDataSource:LocalDataSource
): Repository {
    private val user = User("","","",0,0,"")

    override fun setName(username: String, surname: String): Resource<Unit> {
        return try{
            user.name = username
            user.surname = surname
            Resource.Success(Unit)
        }catch (throwable: Throwable){
            Resource.Error(throwable)
        }
    }

    override fun deleteUser() {
        localDataSource.deleteUser()
    }

    override fun isUserExists(): Boolean =localDataSource.isUserExist()

    override fun setDetail(dateOfBirth: String, weight: Int, height: Int): Resource<Unit> {
        return try{
            user.date = dateOfBirth
            user.weight = weight
            user.height = height
            Resource.Success(Unit)
        }catch (throwable: Throwable){
            Resource.Error(throwable)
        }
    }

    override fun setAbout(about: String): Resource<Unit> {
        return try{
            user.about = about
            Log.d("RepositoryAdil", user.toString())
            setUser(user)
        }catch (throwable: Throwable){
            Resource.Error(throwable)
        }
    }

    override fun setUser(user: User): Resource<Unit> {
        return try {
            localDataSource.saveUser(user)
            Resource.Success(Unit)
        }catch (throwable: Throwable){
            Resource.Error(throwable)
        }
    }


    override fun getMyUser(): User? = localDataSource.getUser()


}