package com.example.myapplication.feature.data

import com.example.myapplication.api.CovidApi
import com.example.myapplication.feature.data.dto.toDomainModel
import com.example.myapplication.feature.domain.entity.Country
import com.example.myapplication.feature.domain.entity.DetailOfCountry
import kotlinx.coroutines.delay

interface CountryRemoteDataSource{
    suspend fun getAllContries(): List<Country>

    suspend fun getDetailsOfCountry(country: String): List<DetailOfCountry>
}

class CountryRemoteDataSourceImpl(
    private val covidApi: CovidApi
):CountryRemoteDataSource{
    override suspend fun getAllContries(): List<Country> {
        delay(1000)
        return covidApi.getAllContries().map {
            it.toDomainModel()
        }
    }

    override suspend fun getDetailsOfCountry(country: String): List<DetailOfCountry> {
        delay(1000)
        return covidApi.getDetailsOfCountry(country).map {
           it.toDomainModel()
        }
    }
}