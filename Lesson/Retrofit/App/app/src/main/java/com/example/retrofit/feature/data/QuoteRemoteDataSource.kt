package com.example.retrofit.feature.data

import com.example.retrofit.api.QuoteApi
import com.example.retrofit.feature.data.dto.toDomainModel
import com.example.retrofit.feature.domain.Quote
import kotlinx.coroutines.delay

interface QuoteRemoteDataSource {
    suspend fun getQuoteOfDay(): Quote
}

class QuoteRemoteDataSourceImpl(
    private val quoteApi: QuoteApi
) : QuoteRemoteDataSource {

    override suspend fun getQuoteOfDay(): Quote {
        delay(1000)
        return quoteApi.getQuoteOfDay().toDomainModel()
    }
}