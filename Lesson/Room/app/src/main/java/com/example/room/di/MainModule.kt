package com.example.room.di

import com.example.room.MainRepository
import com.example.room.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single {
        MainRepository(userDao = get())
    }

    viewModel {
        MainViewModel(
            repository = get()
        )
    }
}