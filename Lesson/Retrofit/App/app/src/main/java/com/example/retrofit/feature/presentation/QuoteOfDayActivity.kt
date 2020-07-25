package com.example.retrofit.feature.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import com.example.retrofit.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class QuoteOfDayActivity : AppCompatActivity() {

    private val viewModel: QuoteOfDayViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.quoteOfDay.observe(this, ::handleState)
    }

    private fun handleState(resource: Resource<QuoteItem>) {
        loaderLayout.isVisible = resource is Resource.Loading

        when (resource) {
            is Resource.Success -> updateUi(resource.data)
            is Resource.Error -> showError(resource.message)
        }
    }

    private fun updateUi(quoteItem: QuoteItem) {
        authorTextView.text = quoteItem.author
        quoteTextView.text = quoteItem.text
    }

    private fun showError(message: String) {
        authorTextView.text = null
        quoteTextView.text = null

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}