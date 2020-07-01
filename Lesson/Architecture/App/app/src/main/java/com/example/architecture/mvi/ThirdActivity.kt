package com.example.architecture.mvi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture.NameAdapter
import com.example.architecture.R
import com.example.architecture.Repository
import com.example.architecture.mvp.FirstActivity
import com.example.architecture.mvvm.SecondActivity
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_third.*
import kotlinx.android.synthetic.main.activity_third.button
import kotlinx.android.synthetic.main.activity_third.recyclerView

class ThirdActivity : AppCompatActivity(), NameAdapter.OnNameClickListener {
    private val repository : Repository = Repository()
    private val mainIntent : MainIntent = MainIntent(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)


        val namesAdapter =
            NameAdapter(this, repository.getNames())
        recyclerView.adapter = namesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        button.setOnClickListener{
            mainIntent.onAction(MainAction.ButtonClick)
        }

        mainIntent.mainState.observe(this, Observer {state ->
            when (state){
                is MainState.ButtonState -> {
                    if (state.isClicked){
                        val intent  = Intent(this, FirstActivity::class.java)
                        startActivity(intent)
                    }
                }
                is MainState.ToastEvent -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }

        })

    }



    override fun onNameClicked(position: Int) {
        mainIntent.onAction(MainAction.ListItemClick(position))
    }
}