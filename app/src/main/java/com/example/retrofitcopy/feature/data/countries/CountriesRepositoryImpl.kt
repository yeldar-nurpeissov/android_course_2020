package com.example.retrofitcopy.feature.data.countries

import com.example.retrofitcopy.feature.data.countries.CountriesRemoteDataSource
import com.example.retrofitcopy.feature.domain.countries.Country
import com.example.retrofitcopy.feature.domain.countries.CountriesRepository

class CountriesRepositoryImpl (
    private val remoteDataSource: CountriesRemoteDataSource
) : CountriesRepository {
    override suspend fun getCountries(): List<Country> =
        remoteDataSource.getCountries()

}