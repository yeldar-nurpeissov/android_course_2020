package com.example.retrofit.feature.covid19.data

import com.example.retrofit.feature.covid19.domain.entity.CountryDomain
import com.example.retrofit.feature.covid19.domain.CovidRepository
import com.example.retrofit.feature.covid19.domain.entity.CountryDetailsDomain

class CovidRepositoryImpl(
    private val remoteDataSource: CovidRemoteDataSource
): CovidRepository {
    override suspend fun getCountries(): List<CountryDomain> = remoteDataSource.getCountries()

    override suspend fun getDetailsOfCountry(slug: String): List<CountryDetailsDomain> =
        remoteDataSource.getDetailsOfCountry(slug)
}