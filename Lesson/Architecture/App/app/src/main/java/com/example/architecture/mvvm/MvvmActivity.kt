package com.example.architecture.mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture.repo.MainAdapter
import com.example.architecture.repo.MainInteraction
import com.example.architecture.R
import com.example.architecture.repo.Repository
import com.example.architecture.mvi.MviActivity
import kotlinx.android.synthetic.main.activity_mvvm.*

class MvvmActivity : AppCompatActivity(), MainInteraction {

    private lateinit var mainadapter: MainAdapter
    private val viewModel = MvvmViewModel(Repository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)

        mainadapter = MainAdapter(arrayListOf(), this)
        mvvvm_recycler.apply {
            layoutManager = LinearLayoutManager(this@MvvmActivity)
            adapter = mainadapter
        }

        mvvm_btn.setOnClickListener {
            viewModel.onBtnClicked()
        }

        viewModel.nextActivityEvent.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, MviActivity::class.java))
                finish()
            }
        })

        viewModel.toastEvent.observe(this, Observer {
            if (it != null) {
                showToast(it)
            }
        })

        viewModel.repoItems.observe(this, Observer{
            mainadapter.setItems(it)
        })
    }

    override fun onClick(text: String) {
        viewModel.onItemClicked(text)
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}