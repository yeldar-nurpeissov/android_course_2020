package com.example.architecture

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*

// MVP Model (DB) - View (Activity/Fragment) - Presenter (..)
// testing
//
// presenter knows about view, no states

class MainActivity : AppCompatActivity(), MainView {

    private val presenter: MainPresenter = MainPresenterImpl(this, Repository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText.doAfterTextChanged {
            presenter.onTextChanged(it.toString())
        }

        button.setOnClickListener {
            presenter.onButtonClicked()
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

// Model
class Repository {
    private var text: String = ""

    fun formatText(): String {
        return "$text $text"
    }

    fun onTextChanged(text: String) {
        this.text = text
    }
}