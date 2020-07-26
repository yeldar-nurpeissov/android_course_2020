package com.example.myapplication.feature.domain.entity

data class DetailOfCountry(
    val Country: String,
    val CountryCode: String,
    val Confirmed: Int,
    val Date: String,
    val Deaths: Int,
    val Recovered: Int,
    val Active: Int
)