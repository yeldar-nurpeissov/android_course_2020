package com.example.retrofit.feature.covid19.domain.entity

data class CountryDetailsDomain (
    val City: String,
    val Confirmed: Int,
    val Deaths: Int,
    val Recovered: Int
)