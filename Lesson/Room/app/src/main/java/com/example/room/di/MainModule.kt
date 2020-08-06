package com.example.room.di

import com.example.room.MainRepository
import com.example.room.MainViewModel
import com.example.room.db.UserItemKeyedDataSource
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory {
        UserItemKeyedDataSource(
            userDao = get()
        )
    }

    single {
        MainRepository(
            userDao = get(),
            userItemKeyedDataSource = get()
        )
    }

    viewModel {
        MainViewModel(
            repository = get()
        )
    }
}