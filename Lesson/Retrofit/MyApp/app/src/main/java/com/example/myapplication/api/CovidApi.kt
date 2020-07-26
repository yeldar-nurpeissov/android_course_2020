package com.example.myapplication.api

import com.example.myapplication.feature.data.dto.CountriesRemoteDTO
import com.example.myapplication.feature.data.dto.DetailOfCountryRemoteDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidApi {
    @GET("countries")
    suspend fun getAllContries(): List<CountriesRemoteDTO>

    @GET("live/country/{country}/status/confirmed/date/2020-01-01T00:00:00Z")
    suspend fun getDetailsOfCountry(@Path("country") country: String): List<DetailOfCountryRemoteDto>
}