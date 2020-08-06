package com.example.room.db.migaration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room.db.entity.MyUserTable


val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE $MyUserTable ADD COLUMN age INTEGER NOT NULl DEFAULT 0")
    }
}