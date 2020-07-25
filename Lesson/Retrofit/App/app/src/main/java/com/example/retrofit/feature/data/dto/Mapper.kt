package com.example.retrofit.feature.data.dto

import com.example.retrofit.feature.domain.Quote

fun QuoteRemoteDTO.toDomainModel(): Quote {
    return Quote(
        author = quoteAuthor,
        quote = quoteText
    )
}