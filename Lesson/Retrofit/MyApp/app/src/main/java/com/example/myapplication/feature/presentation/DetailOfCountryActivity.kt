package com.example.myapplication.feature.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.feature.presentation.entity.DetailOfCountryItem
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

const val EXTRA_COUNTRY_NAME = "EXTRA_COUNTRY_NAME"

class DetailOfCountryActivity:AppCompatActivity() {
    private val viewModel:DetailOfCountryViewModel by viewModel()

    companion object {
        fun getStartIntent(context: Context, countryName: String): Intent {
            return Intent(context, DetailOfCountryActivity::class.java)
                .putExtra(EXTRA_COUNTRY_NAME, countryName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val country = intent.getStringExtra(EXTRA_COUNTRY_NAME)
        viewModel.setCountry(country!!)

        detailSwipeRefresh.setOnRefreshListener {
            viewModel.setCountry(country)
            detailSwipeRefresh.isRefreshing = false
        }
        observe()
    }

    private fun observe(){
        viewModel.detailOfCountry.observe(this, Observer {resourse->
            detailProgressBar.isVisible = resourse is Resource.Loading
            when(resourse){
                is Resource.Success-> updateUi(resourse.data)
                is Resource.Error -> showError(resourse.message)
            }
        })
    }

    private fun updateUi(list: List<DetailOfCountryItem>){
        if (list.isNotEmpty()) {
            val latest = list[list.size - 1]
            countryDetailNameTextView.text = latest.Country
            DateDetailTextView.text = latest.Date
            confirmedDetailTextView.text = "Confirmed: ${latest.Confirmed}"
            activeDetailTextView.text = "Active: ${latest.Active}"
            deathsDetailTextView.text = "Deaths: ${latest.Deaths}"
            recoveredDetailTextView.text = "Recovered: ${latest.Recovered}"
        }else{
            countryDetailNameTextView.text = "No data"
        }

    }

    private fun showError(error: String){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}