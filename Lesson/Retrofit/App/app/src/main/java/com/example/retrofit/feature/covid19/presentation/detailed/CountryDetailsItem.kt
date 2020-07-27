package com.example.retrofit.feature.covid19.presentation.detailed

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryDetailsItem(
    val city: String,
    val deaths: Int,
    val confirmed: Int,
    val recovered: Int
) : Parcelable