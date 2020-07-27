package com.example.retrofit.feature.covid19.domain

import com.example.retrofit.feature.covid19.domain.entity.CountryDetailsDomain

class GetDetailsOfCountryUseCase(
    private val repository: CovidRepository
) {
    suspend operator fun invoke(slug: String): List<CountryDetailsDomain> {
        return repository.getDetailsOfCountry(slug)
    }
}