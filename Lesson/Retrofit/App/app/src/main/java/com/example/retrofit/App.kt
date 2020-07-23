package com.example.retrofit

import android.app.Application
import com.example.retrofit.di.quoteModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(quoteModule)
        }
    }
}