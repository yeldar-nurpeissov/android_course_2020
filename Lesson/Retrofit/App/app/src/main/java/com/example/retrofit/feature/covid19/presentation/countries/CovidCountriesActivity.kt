package com.example.retrofit.feature.covid19.presentation.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.R
import com.example.retrofit.feature.covid19.presentation.countries.adapter.CountriesAdapter
import com.example.retrofit.feature.covid19.presentation.countries.adapter.CountriesClickListener
import com.example.retrofit.feature.covid19.presentation.detailed.CountryDetailsActivity
import kotlinx.android.synthetic.main.activity_covid_countries.*
import kotlinx.android.synthetic.main.activity_main.loaderLayout
import org.koin.android.viewmodel.ext.android.viewModel

class CovidCountriesActivity : AppCompatActivity(), CountriesClickListener {

    private val viewModel: CovidCountriesViewModel by viewModel()
    private lateinit var countriesAdapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_countries)

        initRecycler()
        initObservers()
    }

    private fun initObservers(){
        viewModel.countriesState.observe(this, ::handleState)
    }

    private fun handleState(event: CountriesEvent<List<CountryItem>>) {
        loaderLayout.isVisible = event is CountriesEvent.Loading

        when(event) {
            is CountriesEvent.Error -> showLog(event.message)
            is CountriesEvent.Success -> fillRecycler(event.data)
            is CountriesEvent.ToDetailActivity -> nextActivity(event.slug)
        }
    }

    private fun initRecycler(){
        countriesRecyclerView.apply {
            countriesAdapter = CountriesAdapter(ArrayList(), this@CovidCountriesActivity)
            layoutManager = LinearLayoutManager(this@CovidCountriesActivity)
            adapter = countriesAdapter

            covidCountriesSwipeContainer.setOnRefreshListener {
                viewModel.onFetchCountries()
                if (covidCountriesSwipeContainer.isRefreshing)
                    covidCountriesSwipeContainer.isRefreshing = false
            }
        }
    }

    private fun fillRecycler(countries: List<CountryItem>){
        Log.e("TAG", "Countries added")
        countriesAdapter.setCountries(countries)
    }

    private fun showLog(message : String) {
        Log.e("TAG", message)
    }

    private fun nextActivity(slug: String){
        startActivity(CountryDetailsActivity.intent(this, slug))
    }

    override fun onClick(slug: String) {
        viewModel.onCountryClicked(slug)
    }

}