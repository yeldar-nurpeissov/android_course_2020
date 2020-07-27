package com.example.retrofit.api

import com.example.retrofit.feature.covid19.data.dto.CountryDetailsRemoteDTO
import com.example.retrofit.feature.covid19.data.dto.CountryRemoteDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidApi {
    @GET("countries")
    suspend fun getCovidCountries(): List<CountryRemoteDTO>

    @GET("live/country/{country_slug}/status/confirmed")
    suspend fun getDetailOfCountry(
        @Path("country_slug") slug: String
    ): List<CountryDetailsRemoteDTO>
}