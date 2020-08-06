package com.example.room.db.converter

import androidx.room.TypeConverter
import com.example.room.db.entity.AccessLevel

class AccessLevelConverter {

    @TypeConverter
    fun convertAccessLevelToString(accessLevel: AccessLevel): String {
        return accessLevel.name
    }

    @TypeConverter
    fun stringToAccessLevel(name: String): AccessLevel {
        return AccessLevel.values().find { it.name == name } ?: AccessLevel.GUEST
    }
}