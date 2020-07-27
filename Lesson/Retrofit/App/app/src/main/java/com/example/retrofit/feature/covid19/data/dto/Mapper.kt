package com.example.retrofit.feature.covid19.data.dto

import com.example.retrofit.feature.covid19.domain.entity.CountryDetailsDomain
import com.example.retrofit.feature.covid19.domain.entity.CountryDomain

fun CountryRemoteDTO.toDomain(): CountryDomain {
    return CountryDomain(
        Country = Country,
        ISO2 = ISO2,
        Slug = Slug
    )
}

fun CountryDetailsRemoteDTO.toDomain(): CountryDetailsDomain {
    return CountryDetailsDomain(
        City = City,
        Deaths = Deaths,
        Confirmed = Confirmed,
        Recovered = Recovered
    )
}
