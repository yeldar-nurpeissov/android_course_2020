package com.example.retrofitcopy.feature.data.details

import com.example.retrofitcopy.api.CovidApi
import com.example.retrofitcopy.feature.data.dto.toDomainModel
import com.example.retrofitcopy.feature.domain.details.Detail

interface DetailsRemoteDataSource {
    suspend fun getDetails(country: String) : List<Detail>
}

class RetrofitDetailsRemoteDataSource(
    private val covidApi: CovidApi
) : DetailsRemoteDataSource {
    override suspend fun getDetails(country: String): List<Detail> {
        return covidApi.getDetails(country).toDomainModel()
    }

}