package com.example.architecture.mvi

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
import com.example.architecture.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_mvi.*

class MviActivity : AppCompatActivity(), MainInteraction {

    private lateinit var madapter: MainAdapter
    private val viewModel = MviViewModel(Repository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvi)

        madapter = MainAdapter(arrayListOf(), this)
        mvi_recycler.apply {
            layoutManager = LinearLayoutManager(this@MviActivity)
            adapter = madapter
        }

        mvi_btn.setOnClickListener {
            viewModel.onAction(MviAction.BtnClick)
        }

        viewModel.mainState.observe(this, Observer {
            when(it) {
                is MviState.ToastEvent -> showToast(it.text)
                is MviState.NextActivity -> nextActivity()
                is MviState.GetItemsSuccess -> setItems(it.items)
            }
        })
    }

    override fun onClick(text: String) {
        viewModel.onAction(MviAction.ItemClick(text))
    }

    fun setItems(items: ArrayList<String>) {
        madapter.setItems(items)
    }

    private fun nextActivity() {
        startActivity(Intent(this, MvpActivity::class.java))
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}