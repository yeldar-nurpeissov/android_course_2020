package com.example.myapplication.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.feature.domain.GetAllCountriesUseCase
import com.example.myapplication.feature.presentation.entity.CountryItem
import com.example.myapplication.feature.presentation.entity.toPresentationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllCountryViewModel(
    private val getAllCountriesUseCase: GetAllCountriesUseCase
):ViewModel() {
    val allCountries: LiveData<Resource<List<CountryItem>>> get() = _allCountries
    private val _allCountries = MutableLiveData<Resource<List<CountryItem>>>()

    val nextActivity: LiveData<String> get() = _nextActivity
    private val _nextActivity = MutableLiveData<String>()

    init{

        getAllCountries()
    }

    fun getAllCountries(){
        viewModelScope.launch(Dispatchers.IO) {
            _allCountries.postValue(Resource.Loading)
            try {
                val listCountries = getAllCountriesUseCase()
                _allCountries.postValue(Resource.Success(listCountries.map { it.toPresentationModel() }))
            }catch (throwable: Throwable){
                _allCountries.postValue(Resource.Error(throwable.message ?: "Something went wrong"))
            }
        }
    }

    fun goToDetail(country: String){
        _nextActivity.value = country
    }
}

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val message: String) : Resource<T>()
}