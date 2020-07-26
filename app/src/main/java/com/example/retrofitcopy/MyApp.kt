package com.example.retrofitcopy

import android.app.Application
import com.example.retrofitcopy.di.covidModule
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(covidModule)
        }
    }
}