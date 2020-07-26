package com.example.myapplication.feature.data

import com.example.myapplication.feature.domain.entity.Country
import com.example.myapplication.feature.domain.CountryRepository
import com.example.myapplication.feature.domain.entity.DetailOfCountry

class CountryRepositoryImpl(
    private val remoteDataSource: CountryRemoteDataSource
):CountryRepository {
    override suspend fun getAllContries(): List<Country> {
        return remoteDataSource.getAllContries()
    }

    override suspend fun getDetailsOfCountry(country: String): List<DetailOfCountry> {
        return remoteDataSource.getDetailsOfCountry(country)
    }

}