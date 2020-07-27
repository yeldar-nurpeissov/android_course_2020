package com.example.retrofit.feature.covid19.domain

import com.example.retrofit.feature.covid19.domain.entity.CountryDomain

class GetCountriesUseCase(
    private val repository: CovidRepository
) {
    suspend operator fun invoke(): List<CountryDomain> {
        return repository.getCountries()
    }
}