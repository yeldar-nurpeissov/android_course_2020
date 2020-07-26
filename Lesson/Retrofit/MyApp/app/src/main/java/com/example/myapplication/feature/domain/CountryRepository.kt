package com.example.myapplication.feature.domain

import com.example.myapplication.feature.domain.entity.Country
import com.example.myapplication.feature.domain.entity.DetailOfCountry

interface CountryRepository {
    suspend fun getAllContries(): List<Country>

    suspend fun getDetailsOfCountry(country: String): List<DetailOfCountry>
}