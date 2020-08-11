package com.example.worker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        showNotificationButton.setOnClickListener {
            ShowNotificationWorker.launch(this, 5000)
        }
    }
}