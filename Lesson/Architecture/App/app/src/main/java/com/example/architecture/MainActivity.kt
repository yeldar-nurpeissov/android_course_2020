package com.example.architecture

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

// MVVM: Model (DB) - View (Activity/Fragment || xml) - ViewModel(..)
// no view reference, state

// lot of entry points and states, no machine state

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel = MainViewModel(Repository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText.doAfterTextChanged {
            viewModel.onTextChanged(it.toString())
        }

        button.setOnClickListener {
            viewModel.onButtonClicked()
        }

        viewModel.toastEvent.observe(this, Observer { message ->
            showToast(message ?: "Null message")
        })

        viewModel.loadingState.observe(this, Observer {
            progressBar.isVisible = it ?: false
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

// Model
class Repository {
    private var text: String = ""

    fun getFormattedText(): String {
        return "$text $text"
    }

    fun onTextChanged(text: String) {
        this.text = text
    }
}