package com.example.retrofitcopy.feature.domain.details

interface DetailsRepository {

    suspend fun getDetails(country: String) : List<Detail>
}