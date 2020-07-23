package com.example.retrofit.feature.domain

interface QuoteRepository {
    suspend fun getQuoteOfDay(): Quote
}