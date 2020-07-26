package com.example.retrofitcopy.feature.domain.countries

class GetCountriesUseCase(
    private val repository: CountriesRepository
) {
    suspend operator fun invoke() : List<Country> = repository.getCountries()
}