package com.example.retrofit.feature.covid19.presentation.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.feature.covid19.domain.GetCountriesUseCase
import com.example.retrofit.feature.covid19.presentation.toPresentationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CovidCountriesViewModel(
    private val getCountriesUseCase: GetCountriesUseCase
): ViewModel() {

    private val _countriesState = MutableLiveData<CountriesEvent<List<CountryItem>>>().also {
        onFetchCountries()
    }
    val countriesState: LiveData<CountriesEvent<List<CountryItem>>> get() =  _countriesState

    fun onFetchCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            _countriesState.postValue(CountriesEvent.Loading)
            try {
                val countries = getCountriesUseCase()
                _countriesState.postValue(CountriesEvent.Success(countries.toPresentationModel()))
            }catch (throwable: Throwable){
                _countriesState.postValue(CountriesEvent.Error(throwable.message?:"Oops..."))
            }
        }
    }

    fun onCountryClicked(slug: String){
        _countriesState.postValue(CountriesEvent.ToDetailActivity(slug))
    }
}

sealed class CountriesEvent<out T> {
    object Loading : CountriesEvent<Nothing>()
    class ToDetailActivity(val slug: String): CountriesEvent<Nothing>()
    class Success<T>(val data: T) : CountriesEvent<T>()
    class Error<T>(val message: String) : CountriesEvent<T>()
}