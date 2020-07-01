package com.example.architecture.mvi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture.MainActivity
import com.example.architecture.R
import com.example.architecture.adapter.MyAdapter
import com.example.architecture.mvp.Repository
import com.example.architecture.mvvm.MVVMActivity
import kotlinx.android.synthetic.main.activity_main.*

class MviActivity : AppCompatActivity() {

    private val viewModel = MviViewModel(Repository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "MVI"

        val adapter = MyAdapter(this)
        viewModel.data.observe(this, Observer {state->
            when (state){
                is MainState.GetData -> adapter.setItems(state.list)
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.onAction(MainAction.GetData)

        button.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
    }
}