package com.example.architecture.mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture.R
import com.example.architecture.adapter.MyAdapter
import com.example.architecture.mvi.MviActivity
import com.example.architecture.mvp.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MVVMActivity : AppCompatActivity() {

    private val viewModel = MvvmViewModel(Repository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "MVVM"

        val adapter = MyAdapter(this)
        viewModel.data.observe(this , Observer {
            adapter.setItems(it)
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.getData()

        button.setOnClickListener { startActivity(Intent(this, MviActivity::class.java)) }
    }
}