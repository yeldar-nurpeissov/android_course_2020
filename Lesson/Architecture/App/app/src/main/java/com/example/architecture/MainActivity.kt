package com.example.architecture

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*

// MVC Model (DB) - View (xml) - Controller (Activity/Fragment)
// maintainability, testability

class MainActivity : AppCompatActivity() {

    private val repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText.doAfterTextChanged {
            repository.onTextChanged(it.toString())
        }

        button.setOnClickListener {
            val text = editText.text.toString()
            val formattedText = repository.formatText(text)
            Toast.makeText(this, formattedText, Toast.LENGTH_SHORT).show()
        }
    }
}

// Model
class Repository {
    fun formatText(text: String): String {
        return "$text $text"
    }

    fun onTextChanged(text: String) {

    }
}