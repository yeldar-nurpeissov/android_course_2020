package com.example.retrofitcopy.feature.presentation.countries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitcopy.R
import com.example.retrofitcopy.feature.domain.countries.Country
import com.example.retrofitcopy.feature.presentation.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

class CountriesActivity : AppCompatActivity(),
    CountriesAdapter.OnCountryClickListener {

    private val viewModel : CountriesViewModel by viewModel()
    val countriesAdapter =
        CountriesAdapter(
            this,
            ArrayList()
        )
    var countriesData : List<Country> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.countriesState.observe(this, ::handleState)


        recyclerView.adapter = countriesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            viewModel.refresh()
        }
    }

    private fun handleState(resource: Resource<List<Country>>) {
        loaderLayout.isVisible = resource is Resource.Loading

        when (resource) {
            is Resource.Success -> {
                countriesData = resource.data
                countriesData = countriesData.sortedWith(compareBy { it.countryName })
                updateUi(countriesData)
            }

            is Resource.Error -> showError(resource.message)
        }
    }

    private fun updateUi(countries: List<Country>) {
        countriesAdapter.updateData(countries)
    }

    private fun showError(message: String) {
        // # todo: clear ui on error
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCountryClicked(position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        var countryName = countriesData[position].slug
        intent.putExtra("countryName", countryName)
        startActivity(intent)
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}