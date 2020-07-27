package com.example.retrofit.feature.covid19.domain

import com.example.retrofit.feature.covid19.domain.entity.CountryDetailsDomain
import com.example.retrofit.feature.covid19.domain.entity.CountryDomain

interface CovidRepository {
    suspend fun getCountries(): List<CountryDomain>
    suspend fun getDetailsOfCountry(slug: String): List<CountryDetailsDomain>
}