package com.example.solid.main.data

import com.example.solid.core.NetworkInfo
import com.example.solid.main.domain.MainRepository

class MainRepositoryImpl(
    val localDataSource: MainLocalDataSource,
    val remoteDataSource: MainRemoteDataSource,
    val networkInfo: NetworkInfo
) : MainRepository {
    override fun format(): String {
        return if (networkInfo.isConnected()) {
            remoteDataSource.getFormattedData()
        } else {
            localDataSource.getFromDB()
        }
    }

    override fun save(text: String) {
    }
}