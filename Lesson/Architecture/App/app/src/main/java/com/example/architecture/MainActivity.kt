package com.example.architecture

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

// MVI: Model (DB) - View (Activity/Fragment || xml) - Intent(..)
// single state, single entry point

// no history of states

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel = MainViewModel(Repository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText.doAfterTextChanged {
            viewModel.onAction(MainAction.TextChanged(it.toString()))
        }

        button.setOnClickListener {
            viewModel.onAction(MainAction.ButtonClick)
        }

        viewModel.mainState.observe(this, Observer { state ->
            when (state) {
                is MainState.Loading -> progressBar.isVisible = state.isLoading
                is MainState.ToastEvent -> showToast(state.message)
            }
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