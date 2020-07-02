package com.example.architecture.MVVM

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture.MVI.MainMVIActivity
import com.example.architecture.R
import com.example.architecture.RecyclerAdapter
import com.example.architecture.Repository.Repository
import com.example.architecture.showToast
import kotlinx.android.synthetic.main.activity_mvvm.*

class MainMVVMActivity : AppCompatActivity(), RecyclerAdapter.OnClickService{

    private lateinit var mAdapter: RecyclerAdapter
    private val viewModel = MainViewModel(Repository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)

        showToast(this,"MVVM")

        mAdapter = RecyclerAdapter(this)
        mvvm_list_rv.apply {
            layoutManager = LinearLayoutManager(this@MainMVVMActivity)
            adapter = mAdapter
        }
        mvvm_next_btn.setOnClickListener {
            viewModel.onNextBtnClicked()
        }

        viewModel.nextActivityEvent.observe(this, Observer {
            if(it){
                startActivity(Intent(this, MainMVIActivity::class.java))
                finish()
            }
        })

        viewModel.toastEvent.observe(this, Observer {
            if (it != null) {
                showToast(this, it)
            }
        })

        viewModel.items.observe(this, Observer {
            mAdapter.setItems(it)
        })

    }

    override fun onClickListner(position: Int, tag: String) {
        viewModel.onItemClicked(tag)
    }
}