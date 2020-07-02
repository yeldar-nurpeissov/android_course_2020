package com.example.architecture.MVP

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture.MVVM.MainMVVMActivity
import com.example.architecture.R
import com.example.architecture.RecyclerAdapter
import com.example.architecture.Repository.Repository
import kotlinx.android.synthetic.main.activity_mvp.*

class MainMVPActivity:  AppCompatActivity(), MainView, RecyclerAdapter.OnClickService {

    private lateinit var mAdapter: RecyclerAdapter
    private val presenter = MainPresenterImpl(this, Repository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)

        showToast("MVP")

        mAdapter = RecyclerAdapter(this)
        mvp_list_rv.apply {
            layoutManager = LinearLayoutManager(this@MainMVPActivity)
            adapter = mAdapter
        }

        presenter.onDataLoaded()

        mvp_next_btn.setOnClickListener {
            presenter.onButtonClicked()
        }

    }

    override fun setAdapter(list: List<String>) {
        mAdapter.setItems(list)
    }

    override fun nextActivity() {
        startActivity(Intent(this, MainMVVMActivity::class.java))
        finish()
    }

    override fun onClickListner(position: Int, tag: String) {
        presenter.onItemClicked(tag)
    }

    override fun showToast(text: String) {
        Toast.makeText(this,text, Toast.LENGTH_SHORT).show()
    }
}
