package com.example.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.room.db.converter.AccessLevelConverter
import com.example.room.db.dao.UserDao
import com.example.room.db.entity.User

@Database(
    entities = [User::class], version = 2
)
@TypeConverters(
    AccessLevelConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}