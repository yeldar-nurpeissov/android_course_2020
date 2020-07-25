package com.example.retrofit.feature.presentation

import com.example.retrofit.feature.domain.Quote

fun Quote.toPresentationModel(): QuoteItem = QuoteItem(
    text = quote,
    author = author
)