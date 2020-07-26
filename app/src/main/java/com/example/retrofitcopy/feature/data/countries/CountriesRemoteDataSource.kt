package com.example.retrofitcopy.feature.data.countries

import com.example.retrofitcopy.api.CovidApi
import com.example.retrofitcopy.feature.data.dto.toDomainModel
import com.example.retrofitcopy.feature.domain.countries.Country
import kotlinx.coroutines.delay


interface CountriesRemoteDataSource {
    suspend fun getCountries() : List<Country>
}

class RetrofitCountriesRemoteDataSource(
    private val covidApi : CovidApi
) : CountriesRemoteDataSource {

    override suspend fun getCountries(): List<Country> {
        delay(1000)
        return covidApi.getCountries().toDomainModel()
    }

}