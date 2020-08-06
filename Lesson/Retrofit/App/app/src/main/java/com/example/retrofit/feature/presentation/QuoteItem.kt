package com.example.retrofit.feature.presentation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuoteItem(
    val text: String,
    val author: String
) : Parcelable