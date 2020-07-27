package com.example.retrofit.feature.covid19.presentation.countries

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryItem(
    val country: String,
    val slug: String,
    val iso2: String
): Parcelable