package com.example.retrofitcopy.feature.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitcopy.feature.domain.details.Detail
import com.example.retrofitcopy.feature.domain.details.GetDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    var countryName : String = ""

    val detailsState : MutableLiveData<Resource<List<Detail>>> =
        MutableLiveData<Resource<List<Detail>>>().also{
            makeRequest(it)
        }

    fun refresh(){
        detailsState.also {
            makeRequest(it)
        }
    }

    fun setCountry(countryName: String){
        this.countryName = countryName
    }


    private fun makeRequest(it: MutableLiveData<Resource<List<Detail>>>) {
        viewModelScope.launch(Dispatchers.IO) {
            it.postValue(Resource.Loading)
            try {
                val countries = getDetailsUseCase.getDetailsByCountry(countryName)
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