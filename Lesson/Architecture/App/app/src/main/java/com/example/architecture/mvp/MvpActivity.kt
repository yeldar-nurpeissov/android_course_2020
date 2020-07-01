package com.example.architecture.mvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture.repo.MainAdapter
import com.example.architecture.repo.MainInteraction
import com.example.architecture.R
import com.example.architecture.repo.Repository
import com.example.architecture.mvvm.MvvmActivity
import kotlinx.android.synthetic.main.activity_mvp.*

class MvpActivity : AppCompatActivity(), MvpView, MainInteraction {

    private lateinit var mainadapter: MainAdapter
    private val presenter = MvpPresenterImpl(this,
        Repository()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)

        mainadapter = MainAdapter(arrayListOf(), this)
        mvp_recycler.apply {
            layoutManager = LinearLayoutManager(this@MvpActivity)
            adapter = mainadapter
        }
        presenter.onDataReady()

        mvp_btn.setOnClickListener {
            presenter.onButtonClicked()
        }
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun setAdapterItems(list: ArrayList<String>) {
        mainadapter.setItems(list)
    }

    override fun nextActivity() {
        startActivity(Intent(this, MvvmActivity::class.java))
        finish()
    }

    override fun onClick(text: String) {
        presenter.onItemClicked(text)
    }

}

interface MvpView {
    fun showToast(text: String)
    fun setAdapterItems(list: ArrayList<String>)
    fun nextActivity()
}