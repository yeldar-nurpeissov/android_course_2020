package com.example.retrofit.feature.data

import com.example.retrofit.feature.domain.Quote
import com.example.retrofit.feature.domain.QuoteRepository

class QuoteRepositoryImpl(
    private val remoteDataSource: QuoteRemoteDataSource
) : QuoteRepository {
    override suspend fun getQuoteOfDay(): Quote =
        remoteDataSource.getQuoteOfDay()
}