package com.example.cleanarchitecture

import android.app.Application
import com.example.cleanarchitecture.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CleanArchApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CleanArchApplication)
            modules(mainModule)
        }
    }
}