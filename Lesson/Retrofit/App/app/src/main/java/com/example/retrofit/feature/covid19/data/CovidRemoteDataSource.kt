package com.example.retrofit.feature.covid19.data

import com.example.retrofit.api.CovidApi
import com.example.retrofit.feature.covid19.data.dto.toDomain
import com.example.retrofit.feature.covid19.domain.entity.CountryDetailsDomain
import com.example.retrofit.feature.covid19.domain.entity.CountryDomain
import kotlinx.coroutines.delay

interface CovidRemoteDataSource {
    suspend fun getCountries(): List<CountryDomain>
    suspend fun getDetailsOfCountry(slug: String): List<CountryDetailsDomain>
}

class CovidRemoteDataSourceRetrofit(
    private val api: CovidApi
): CovidRemoteDataSource {
    override suspend fun getCountries(): List<CountryDomain> {
        //wait
        delay(1000)
        return api.getCovidCountries().map {
            it.toDomain()
        }
    }

    override suspend fun getDetailsOfCountry(slug: String): List<CountryDetailsDomain> {
        delay(1000)
        return api.getDetailOfCountry(slug).map {
            it.toDomain()
        }
    }

}