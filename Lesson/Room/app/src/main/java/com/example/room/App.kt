package com.example.room

import android.app.Application
import com.example.room.di.dbModule
import com.example.room.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                mainModule,
                dbModule
            )
        }
    }
}