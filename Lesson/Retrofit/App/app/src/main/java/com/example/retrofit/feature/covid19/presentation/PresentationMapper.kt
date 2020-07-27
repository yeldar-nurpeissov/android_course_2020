package com.example.retrofit.feature.covid19.presentation

import com.example.retrofit.feature.covid19.domain.entity.CountryDetailsDomain
import com.example.retrofit.feature.covid19.domain.entity.CountryDomain
import com.example.retrofit.feature.covid19.presentation.countries.CountryItem
import com.example.retrofit.feature.covid19.presentation.detailed.CountryDetailsItem

fun CountryDomain.toPresentationModel() : CountryItem {
    return CountryItem(
        country = Country,
        iso2 = ISO2,
        slug = Slug
    )
}

fun List<CountryDomain>.toPresentationModel() : List<CountryItem> {
    return this.map {
        it.toPresentationModel()
    }
}

fun CountryDetailsDomain.toPresentationModel() : CountryDetailsItem {
    return CountryDetailsItem(
        city = City,
        deaths = Deaths,
        confirmed = Confirmed,
        recovered = Recovered
    )
}