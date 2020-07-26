package com.example.myapplication.feature.presentation.entity

import com.example.myapplication.feature.data.dto.toDomainModel
import com.example.myapplication.feature.domain.entity.Country
import com.example.myapplication.feature.domain.entity.DetailOfCountry

fun Country.toPresentationModel():CountryItem{
    return CountryItem(
        Country= this.Country
    )
}

fun DetailOfCountry.toPresentationModel():DetailOfCountryItem{
    return DetailOfCountryItem(
        Country = this.Country,
        Confirmed = this.Confirmed,
        Deaths = this.Deaths,
        Date = this.Date,
        Recovered = this.Recovered,
        Active = this.Active
    )
}