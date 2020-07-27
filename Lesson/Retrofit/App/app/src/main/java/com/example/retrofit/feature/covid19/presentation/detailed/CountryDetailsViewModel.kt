package com.example.retrofit.feature.covid19.presentation.detailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.feature.covid19.domain.GetDetailsOfCountryUseCase
import com.example.retrofit.feature.covid19.presentation.toPresentationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryDetailsViewModel(
    private val slug: String,
    private val getDetailsOfCountryUseCase: GetDetailsOfCountryUseCase
): ViewModel() {

    private val _detailsState  = MutableLiveData<DetailsEvent<List<CountryDetailsItem>>>().also {
        onFetchDetails()
    }
    val detailsState: LiveData<DetailsEvent<List<CountryDetailsItem>>> get() =
        _detailsState

    fun onFetchDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            _detailsState.postValue(DetailsEvent.Loading)
            try {
                val details = getDetailsOfCountryUseCase(slug).map {
                    it.toPresentationModel()
                }
                _detailsState.postValue(DetailsEvent.Success(details))
            }catch (throwable: Throwable) {
                _detailsState.postValue(DetailsEvent.Error(throwable.message?:"Oops..."))
            }
        }
    }
}

sealed class DetailsEvent<out T> {
    object Loading : DetailsEvent<Nothing>()
    class Success<T>(val data: T) : DetailsEvent<T>()
    class Error<T>(val message: String) : DetailsEvent<T>()
}