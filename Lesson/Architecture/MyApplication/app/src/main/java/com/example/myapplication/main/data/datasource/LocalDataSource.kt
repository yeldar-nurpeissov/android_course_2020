package com.example.myapplication.main.data.datasource

import android.content.SharedPreferences
import android.util.Log
import com.example.myapplication.main.domain.entity.User

interface LocalDataSource{
    fun saveUser(user: User)
    fun getUser():User?
    fun isUserExist():Boolean
    fun deleteUser()
}

class SharedPrefLocalDataSource(private val sharedPreferences: SharedPreferences):LocalDataSource {
    private val KEY = "key_user"
    private val separator = "#####"

    override fun isUserExist(): Boolean =
        sharedPreferences.contains(KEY)

    override fun deleteUser() {
        sharedPreferences.edit().remove(KEY).apply()
    }

    override fun saveUser(user: User) {
        val outputStr = "${user.name}$separator${user.surname}$separator${user.date}$separator"+
                "${user.weight}$separator${user.height}$separator${user.about}"
        sharedPreferences.edit().putString(KEY, outputStr).apply()
    }

    override fun getUser(): User? {
        val useStr = sharedPreferences.getString(KEY,null)
        if (!useStr.isNullOrBlank() && useStr.contains(separator)){
            val userParts = useStr.split(separator)
            val name = userParts[0]
            val surname = userParts[1]
            val date = userParts[2]
            val weight = userParts[3].toInt()
            val height = userParts[4].toInt()
            val about = userParts[5]
            Log.d("RepositoryAdil", "$name $surname $date $weight $height $about")
            return User(name,surname, date, weight, height, about)
        }
        return null
    }

}