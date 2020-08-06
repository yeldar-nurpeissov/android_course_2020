package com.example.retrofit.feature.domain

class GetQuoteOfDayUseCase(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(): Quote = repository.getQuoteOfDay()
}