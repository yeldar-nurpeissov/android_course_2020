package com.example.retrofitcopy.feature.presentation.countries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitcopy.feature.domain.countries.Country
import com.example.retrofitcopy.feature.domain.countries.GetCountriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    val countriesState : MutableLiveData<Resource<List<Country>>> =
        MutableLiveData<Resource<List<Country>>>().also{
            makeRequest(it)
    }

    fun refresh(){
        countriesState.also {
            makeRequest(it)
        }
    }


    private fun makeRequest(it: MutableLiveData<Resource<List<Country>>>) {
        viewModelScope.launch(Dispatchers.IO) {
            it.postValue(Resource.Loading)
            try {
                val countries = getCountriesUseCase()
                it.postValue(
                    Resource.Success(
                        countries
                    )
                )
            } catch (throwable: Throwable) {
                it.postValue(
                    Resource.Error(
                        throwable.message ?: "Something went wrong!!!"
                    )
                )
            }
        }
    }

}



sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val message: String) : Resource<T>()
}