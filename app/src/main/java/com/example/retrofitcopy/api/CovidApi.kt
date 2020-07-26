package com.example.retrofitcopy.api

import com.example.retrofitcopy.feature.data.dto.api_sports.CountriesRemoteDTOX
import com.example.retrofitcopy.feature.data.dto.api_sports.DetailsRemoteDTOX
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidApi {
//    url = google.com/api/v1/
//
//    @GET("/qod") //google.com/qod
//    @GET("qod")//google.com/api/v1/qod

//    https://api.forismatic.com/api/1.0/?method=getQuote&lang=en&format=json
//    https://google.com/api/search?q=123123

    @GET("countries")
    suspend fun getCountries(): CountriesRemoteDTOX

    @GET("history")
    suspend fun getDetails(@Query("country") country: String): DetailsRemoteDTOX
}