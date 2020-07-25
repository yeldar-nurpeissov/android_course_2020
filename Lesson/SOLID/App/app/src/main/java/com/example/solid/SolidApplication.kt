package com.example.solid

import android.app.Application
import com.example.solid.di.mainModule
import org.koin.core.context.startKoin

class SolidApplication  : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { modules(mainModule) }
    }
}