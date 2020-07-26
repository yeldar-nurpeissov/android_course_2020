package com.example.retrofitcopy.feature.presentation.details


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitcopy.R
import com.example.retrofitcopy.feature.domain.details.Detail
import kotlinx.android.synthetic.main.activity_main.*

import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

class DetailsActivity : AppCompatActivity(){

    private val viewModel : DetailsViewModel by viewModel()
    val detailsAdapter = DetailsAdapter(ArrayList())
    var detailsData : List<Detail> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var countryName : String?

        if (intent.getStringExtra("countryName")!=null){
            countryName = intent.getStringExtra("countryName")
        }else{
            countryName = "empty"
        }

        Log.d("COUNTRY_NAME", "onCreate: " + countryName)

        viewModel.setCountry(countryName!!)

        viewModel.detailsState.observe(this, ::handleState)


        recyclerView.adapter = detailsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            viewModel.refresh()
        }
    }

    private fun handleState(resource: Resource<List<Detail>>) {
        loaderLayout.isVisible = resource is Resource.Loading

        when (resource) {
            is Resource.Success -> {
                detailsData = resource.data
                updateUi(detailsData)
            }

            is Resource.Error -> showError(resource.message)
        }
    }

    private fun updateUi(details: List<Detail>) {
        detailsAdapter.updateData(details)
    }

    private fun showError(message: String) {
        // # todo: clear ui on error
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}