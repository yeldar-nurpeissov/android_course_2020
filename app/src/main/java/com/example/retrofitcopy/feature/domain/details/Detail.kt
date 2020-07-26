package com.example.retrofitcopy.feature.domain.details


data class Detail(
    val new: String?,
    val active: Int?,
    val critical: Int?,
    val recovered: Int?,
    val total: Int?,
    val day: String?,
    val time: String?
)