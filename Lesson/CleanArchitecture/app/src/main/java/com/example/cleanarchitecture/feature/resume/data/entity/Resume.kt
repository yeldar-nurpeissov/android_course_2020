package com.example.cleanarchitecture.feature.resume.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Resume(
    var firstName: String = "",
    var lastName: String = "",
    var birthday: String = "",
    var weight: Int = 0,
    var height: Int = 0,
    var aboutMe: String = ""
): Parcelable