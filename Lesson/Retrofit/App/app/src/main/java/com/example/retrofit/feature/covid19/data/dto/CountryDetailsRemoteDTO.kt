package com.example.retrofit.feature.covid19.data.dto

data class CountryDetailsRemoteDTO(
    val Active: Int,
    val City: String,
    val CityCode: String,
    val Confirmed: Int,
    val Country: String,
    val CountryCode: String,
    val Date: String,
    val Deaths: Int,
    val Lat: String,
    val Lon: String,
    val Province: String,
    val Recovered: Int
)