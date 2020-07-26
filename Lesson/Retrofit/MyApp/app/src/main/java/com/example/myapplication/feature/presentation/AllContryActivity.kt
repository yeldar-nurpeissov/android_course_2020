package com.example.myapplication.feature.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.feature.presentation.adapter.CountryAdapter
import com.example.myapplication.feature.presentation.adapter.CountryClickListener
import com.example.myapplication.feature.presentation.entity.CountryItem
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class AllContryActivity : AppCompatActivity(), CountryClickListener {
    private val viewModel: AllCountryViewModel by viewModel()
    private lateinit var countryAdapter:CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countryAdapter = CountryAdapter(this)
        countryRecyclerView.adapter = countryAdapter
        countryRecyclerView.layoutManager =  LinearLayoutManager(this)

        swipeRefresh.setOnRefreshListener {
            viewModel.getAllCountries()
            swipeRefresh.isRefreshing = false
        }

        observe()
    }

    private fun observe(){
        viewModel.allCountries.observe(this, Observer {resource->
            mainProgressBar.isVisible = resource is Resource.Loading

            when(resource){
                is Resource.Success -> updateList(resource.data)
                is Resource.Error ->  showError(resource.message)
            }
        })

        viewModel.nextActivity.observe(this, Observer {
            if (it != null){
                startActivity(DetailOfCountryActivity.getStartIntent(this,it))
            }
        })
    }

    private fun updateList(list: List<CountryItem>){
        countryAdapter.setItems(list)
    }

    private fun showError(error: String){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }


    override fun onClick(countryName: String) {
        viewModel.goToDetail(countryName)
    }
}