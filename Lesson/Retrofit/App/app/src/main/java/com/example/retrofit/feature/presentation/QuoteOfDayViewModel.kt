package com.example.retrofit.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.feature.domain.GetQuoteOfDayUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteOfDayViewModel(
    private val getQuoteOfDayUseCase: GetQuoteOfDayUseCase
) : ViewModel() {

    val quoteOfDay: LiveData<Resource<QuoteItem>> = MutableLiveData<Resource<QuoteItem>>().also {
        viewModelScope.launch(Dispatchers.IO) {
            it.postValue(Resource.Loading)
            try {
                val quote = getQuoteOfDayUseCase()
                it.postValue(Resource.Success(quote.toPresentationModel()))
            } catch (throwable: Throwable) {
                it.postValue(Resource.Error(throwable.message ?: "Something went wrong!!!"))
            }
        }
    }
}

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val message: String) : Resource<T>()
}