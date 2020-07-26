package com.example.myapplication.feature

import android.app.Application
import com.example.myapplication.di.countryModule
import org.koin.core.context.startKoin

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(countryModule)
        }
    }
}