package com.example.myapplication.feature.domain

import com.example.myapplication.feature.domain.entity.Country

class GetAllCountriesUseCase(
    private val repository: CountryRepository
) {
    suspend operator fun invoke():List<Country> =repository.getAllContries()
}