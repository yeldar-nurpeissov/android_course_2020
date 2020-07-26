package com.example.retrofitcopy.feature.domain.countries

import com.example.retrofitcopy.feature.domain.countries.Country
import com.example.retrofitcopy.feature.domain.details.Detail


interface CountriesRepository {

    suspend fun getCountries() : List<Country>


}