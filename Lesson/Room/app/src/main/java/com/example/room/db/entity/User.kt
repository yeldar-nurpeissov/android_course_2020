package com.example.room.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.room.db.converter.AccessLevelConverter

const val MyUserTable = "my_user"

@Entity(tableName = MyUserTable)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val accessLevel: AccessLevel,
    val age:Int
)

enum class AccessLevel {
    GUEST,
    MODERATOR,
    SUPER_ADMIN,
}