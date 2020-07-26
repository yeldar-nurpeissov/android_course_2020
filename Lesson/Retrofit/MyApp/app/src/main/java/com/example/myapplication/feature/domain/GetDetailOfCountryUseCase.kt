package com.example.myapplication.feature.domain

import com.example.myapplication.feature.domain.entity.DetailOfCountry

class GetDetailOfCountryUseCase(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(country: String): List<DetailOfCountry> = repository.getDetailsOfCountry(country)
}