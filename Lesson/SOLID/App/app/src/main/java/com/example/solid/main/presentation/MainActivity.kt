package com.example.solid.main.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.solid.R
import com.example.solid.core.NetworkInfo
import com.example.solid.main.data.*
import com.example.solid.main.domain.FormatUseCase
import com.example.solid.main.domain.MainRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: ViewModel = getViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonTwo.setOnClickListener {
            viewModel.onButtonClicked()
        }
        viewModel.liveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}

fun getViewModel(): ViewModel {
    val useCase = FormatUseCase(getRepo())

    return ViewModel(useCase)
}

fun getRepo(): MainRepository = MainRepositoryImpl(
    networkInfo = getNetworkInfo(),
    remoteDataSource = MainRemoteDataSource(),
    localDataSource = MainSharedPrefDataSource()
)

fun getNetworkInfo(): NetworkInfo {
    return object : NetworkInfo {
        override fun isConnected(): Boolean = false
    }
}

// Presentation
// Domain
// Data