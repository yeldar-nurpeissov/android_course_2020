package com.example.retrofit.api

import com.example.retrofit.feature.data.dto.QuoteRemoteDTO
import retrofit2.http.GET

interface QuoteApi {
//    url = google.com/api/v1/
//
//    @GET("/qod") //google.com/qod
//    @GET("qod")//google.com/api/v1/qod

//    https://api.forismatic.com/api/1.0/?method=getQuote&lang=en&format=json
//    https://google.com/api/search?q=123123

    @GET("api/1.0/?method=getQuote&lang=en&format=json")
    suspend fun getQuoteOfDay(): QuoteRemoteDTO
}