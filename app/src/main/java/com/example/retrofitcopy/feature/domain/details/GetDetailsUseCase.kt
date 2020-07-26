package com.example.retrofitcopy.feature.domain.details

import com.example.retrofitcopy.feature.domain.countries.CountriesRepository

class GetDetailsUseCase(
    private val repository: DetailsRepository
) {
    suspend fun getDetailsByCountry(country: String) : List<Detail> =
        repository.getDetails(country)
}