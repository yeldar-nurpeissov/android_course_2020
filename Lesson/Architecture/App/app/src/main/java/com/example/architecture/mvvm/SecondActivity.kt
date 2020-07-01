package com.example.architecture.mvvm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture.NameAdapter
import com.example.architecture.R
import com.example.architecture.Repository
import com.example.architecture.mvi.ThirdActivity
import kotlinx.android.synthetic.main.activity_second.button
import kotlinx.android.synthetic.main.activity_second.recyclerView

class SecondActivity : AppCompatActivity(), NameAdapter.OnNameClickListener {
    val repository : Repository = Repository()
    val viewModel : ViewModel = ViewModel(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val namesAdapter =
            NameAdapter(this, repository.getNames())
        recyclerView.adapter = namesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        button.setOnClickListener{
            viewModel.onButtonClicked()
        }

        viewModel.buttonClickEvent.observe(this, Observer {
            if (it == true){
                val intent  = Intent(this, ThirdActivity::class.java)
                startActivity(intent)
            }
        })

        viewModel.toastEvent.observe(this, Observer { name ->
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
        })

    }

    override fun onNameClicked(position: Int) {
        viewModel.onListItemClicked(position)
    }
}