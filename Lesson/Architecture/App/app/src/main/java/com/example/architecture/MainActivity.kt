package com.example.architecture

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture.adapter.MyAdapter
import com.example.architecture.mvp.Contract
import com.example.architecture.mvp.Presenter
import com.example.architecture.mvp.Repository
import com.example.architecture.mvvm.MVVMActivity
import kotlinx.android.synthetic.main.activity_main.*

// MVP

class MainActivity : AppCompatActivity(), Contract.View {

    private val presenter = Presenter(this, Repository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "MVP"

        val adapter = MyAdapter(this)
        adapter.setItems(presenter.getData())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        button.setOnClickListener { startActivity(Intent(this, MVVMActivity::class.java)) }
    }

}

