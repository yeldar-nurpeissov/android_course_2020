package com.example.room.di

import androidx.room.Room
import com.example.room.db.AppDatabase
import com.example.room.db.migaration.MIGRATION_1_2
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, "database-name"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    single {
        val database = get<AppDatabase>()
        return@single database.userDao()
    }
}