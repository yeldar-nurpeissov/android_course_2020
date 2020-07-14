package com.example.myapplication.data

import android.util.Log
import com.example.myapplication.core.Resource
import com.example.myapplication.data.datasource.LocalDataSource
import com.example.myapplication.data.entity.User
import com.example.myapplication.domain.Repository

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
            localDataSource.saveUser(user)
            Resource.Success(Unit)
        }catch (throwable: Throwable){
            Resource.Error(throwable)
        }
    }

    override fun getMyUser(): User? = localDataSource.getUser()


}