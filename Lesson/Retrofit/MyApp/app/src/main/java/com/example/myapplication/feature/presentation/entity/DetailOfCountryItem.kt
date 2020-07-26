package com.example.myapplication.feature.presentation.entity

data class DetailOfCountryItem(
    val Country: String,
    val Confirmed: Int,
    val Date: String,
    val Deaths: Int,
    val Recovered: Int,
    val Active: Int
)