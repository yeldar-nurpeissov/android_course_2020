package com.example.myapplication.core

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.data.RepositoryImpl
import com.example.myapplication.data.datasource.LocalDataSource
import com.example.myapplication.data.datasource.SharedPrefLocalDataSource
import com.example.myapplication.domain.*
import com.example.myapplication.presentation.viewmodel.*

class DependencyInjection {

    companion object{
        lateinit var sharedPreferences: SharedPreferences
        @Volatile private var instance: DependencyInjection? = null

        fun getInstance(context: Context): DependencyInjection {
            return instance ?: synchronized(this) {
                instance ?: getDI(context).also { instance = it }
            }
        }

        private fun getDI(context: Context):DependencyInjection{
            sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
            return DependencyInjection()
        }
    }


    fun getNameViewModel() = NameViewModel(
        getSetNameUseCase(),
        getExistUseCase(),
        getGetUseCase()
    )

    fun getDetailViewModel() = DetailViewModel(
        getSetDetailUseCase(),
        getExistUseCase(),
        getGetUseCase()

    )
    fun getAboutViewModel() = AboutViewModel(
        getSetAboutUseCase(),
        getExistUseCase(),
        getGetUseCase()
    )

    fun getGetDetailViewModel() = GetDetailViewModel(
        getGetUseCase(),
        getDeleteUseCase()
    )

    fun getExistViewModel() = EntryViewModel(getExistUseCase())

    fun getDeleteUseCase() = DeleteUseCase(repository)

    private fun getExistUseCase() = ExistUseCase(repository)

    private fun getGetUseCase() = GetUseCase(repository)

    private fun getSetAboutUseCase() = SetAboutUseCase(repository)

    private fun getSetDetailUseCase() = SetDetailUseCase(repository)

    private fun getSetNameUseCase() = SetNameUseCase(repository)

    private val repository: Repository by lazy{
        RepositoryImpl(getLocalDataSource())
    }

    private fun getLocalDataSource(): LocalDataSource{
        return SharedPrefLocalDataSource(sharedPreferences)
    }
}