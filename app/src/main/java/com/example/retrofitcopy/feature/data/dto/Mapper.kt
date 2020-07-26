package com.example.retrofitcopy.feature.data.dto

import com.example.retrofitcopy.feature.data.dto.api_covid19.CountriesRemoteDTO
import com.example.retrofitcopy.feature.data.dto.api_sports.CountriesRemoteDTOX
import com.example.retrofitcopy.feature.data.dto.api_sports.DetailsRemoteDTOX
import com.example.retrofitcopy.feature.domain.countries.Country
import com.example.retrofitcopy.feature.domain.details.Detail


fun CountriesRemoteDTO.toDomainModel(): ArrayList<Country> {

    val domainCountryList = ArrayList<Country>()

    for (country in this) {
        domainCountryList.add(
            Country(
                countryName = country.Country,
                slug = country.Slug
            )
        )
    }

    return domainCountryList
}

fun CountriesRemoteDTOX.toDomainModel(): ArrayList<Country> {
    val domainCountryList = ArrayList<Country>()

    for (country in response) {
        domainCountryList.add(
            Country(
                countryName = country,
                slug = country.toLowerCase()
            )
        )
    }

    return domainCountryList
}

fun DetailsRemoteDTOX.toDomainModel() : ArrayList<Detail> {

    val domainDetailsList = ArrayList<Detail>()

    for (resp in response){
        domainDetailsList.add(
            Detail(
                new = resp.cases.new,
                active = resp.cases.active,
                critical = resp.cases.critical,
                recovered = resp.cases.recovered,
                total = resp.cases.total,
                day = resp.day,
                time = resp.time
            )
        )
    }

    return domainDetailsList
}

