package com.example.retrofitcopy.feature.data.details

import com.example.retrofitcopy.feature.data.details.DetailsRemoteDataSource
import com.example.retrofitcopy.feature.domain.details.Detail
import com.example.retrofitcopy.feature.domain.details.DetailsRepository

class DetailsRepositoryImpl(
    private val remoteDataSource: DetailsRemoteDataSource
) : DetailsRepository{
    override suspend fun getDetails(country: String): List<Detail> {
        return remoteDataSource.getDetails(country)
    }

}