package com.example.retrofit.feature.covid19.presentation.detailed

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.R
import com.example.retrofit.feature.covid19.presentation.detailed.adapter.CountryDetailsAdapter
import kotlinx.android.synthetic.main.activity_country_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CountryDetailsActivity : AppCompatActivity() {

    companion object{
        const val SLUG_IDENTIFIER = "slug"

        fun intent(context: Context, slug: String): Intent{
            return Intent(context, CountryDetailsActivity::class.java).also {
                it.putExtra(SLUG_IDENTIFIER, slug)
            }
        }
    }

    private val viewModel: CountryDetailsViewModel by viewModel{
        parametersOf(intent?.getStringExtra(SLUG_IDENTIFIER))
    }
    private lateinit var detailsAdapter: CountryDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        Log.e("TAG", "${intent?.getStringExtra(SLUG_IDENTIFIER)}")

        initRecycler()
        initObserver()
    }

    private fun initObserver(){
        viewModel.detailsState.observe(this, ::handleState)
    }

    private fun initRecycler(){
        detailsRecyclerView.apply {
            detailsAdapter = CountryDetailsAdapter(ArrayList())
            layoutManager = LinearLayoutManager(this@CountryDetailsActivity)
            adapter = detailsAdapter
        }

        countryDetailsSwipeContainer.setOnRefreshListener {
            viewModel.onFetchDetails()
            if (countryDetailsSwipeContainer.isRefreshing)
                countryDetailsSwipeContainer.isRefreshing = false
        }
    }

    private fun handleState(event: DetailsEvent<List<CountryDetailsItem>>){
        loaderLayout.isVisible = event is DetailsEvent.Loading

        when(event) {
            is DetailsEvent.Error -> showLog(event.message)
            is DetailsEvent.Success -> fillRecycler(event.data)
        }
    }

    private fun showLog(message: String) {
        Log.e("TAG", message)
    }

    private fun fillRecycler(details: List<CountryDetailsItem>) {
        Log.e("TAG", "$details")
        detailsAdapter.setDetails(details)
    }
}