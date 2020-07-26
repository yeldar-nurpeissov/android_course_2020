package com.example.myapplication.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.feature.domain.GetDetailOfCountryUseCase
import com.example.myapplication.feature.presentation.entity.DetailOfCountryItem
import com.example.myapplication.feature.presentation.entity.toPresentationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailOfCountryViewModel(
    private val getDetailOfCountryUseCase: GetDetailOfCountryUseCase
): ViewModel() {
    val detailOfCountry: LiveData<Resource<List<DetailOfCountryItem>>> get() = _detailOfCountry
    private val _detailOfCountry = MutableLiveData<Resource<List<DetailOfCountryItem>>>()

    fun setCountry(country: String){
        viewModelScope.launch(Dispatchers.IO) {
            _detailOfCountry.postValue(Resource.Loading)
            try{
                val listOfDetail = getDetailOfCountryUseCase(country)
                _detailOfCountry.postValue(Resource.Success(listOfDetail.map { it.toPresentationModel() }))
            }catch (throwable: Throwable){
                _detailOfCountry.postValue(Resource.Error(throwable.message?: "Something went wrong"))
            }
        }
    }
}