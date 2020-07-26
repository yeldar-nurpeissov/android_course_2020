package com.example.myapplication.feature.data.dto

import com.example.myapplication.feature.domain.entity.Country
import com.example.myapplication.feature.domain.entity.DetailOfCountry

fun CountriesRemoteDTO.toDomainModel(): Country {
    return Country(
        Country = this.Country,
        ISO2 = this.ISO2
    )
}

fun DetailOfCountryRemoteDto.toDomainModel(): DetailOfCountry {
    return DetailOfCountry(
        Country = this.Country,
        CountryCode = this.CountryCode,
        Confirmed = this.Confirmed,
        Deaths = this.Deaths,
        Date = this.Date,
        Recovered = this.Recovered,
        Active = this.Active
    )
}