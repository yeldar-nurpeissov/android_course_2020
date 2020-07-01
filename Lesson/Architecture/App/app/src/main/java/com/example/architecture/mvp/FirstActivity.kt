package com.example.architecture.mvp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture.NameAdapter
import com.example.architecture.R
import com.example.architecture.Repository
import com.example.architecture.mvvm.SecondActivity
import kotlinx.android.synthetic.main.activity_first.*
import java.util.*
import kotlin.collections.ArrayList


class FirstActivity : AppCompatActivity(), MainView, NameAdapter.OnNameClickListener {

    private val repository : Repository = Repository()
    private val presenter : Presenter = PresenterImpl(this, repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        button.setOnClickListener{
            presenter.onButtonClicked()
        }

        val namesAdapter =
            NameAdapter(this, repository.getNames())
        recyclerView.adapter = namesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onButtonClicked() {
        val intent  = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    override fun onNameClicked(position: Int) {
        presenter.onListItemClicked(position)
    }
}

