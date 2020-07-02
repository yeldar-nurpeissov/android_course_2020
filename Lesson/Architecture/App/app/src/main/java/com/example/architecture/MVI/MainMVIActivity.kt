package com.example.architecture.MVI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture.MVP.MainMVPActivity
import com.example.architecture.R
import com.example.architecture.RecyclerAdapter
import com.example.architecture.Repository.Repository
import com.example.architecture.showToast
import kotlinx.android.synthetic.main.activity_mvi.*

class MainMVIActivity: AppCompatActivity(), RecyclerAdapter.OnClickService {

    private lateinit var mAdapter: RecyclerAdapter
    private val viewModel = MainViewModel(Repository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvi)

        showToast(this, "MVI")

        mAdapter = RecyclerAdapter(this)
        mvi_list_rv.apply{
            layoutManager = LinearLayoutManager(this@MainMVIActivity)
            adapter = mAdapter
        }

        mvi_next_btn.setOnClickListener {
            viewModel.onAction(MainAction.NextBtnClicked())
        }

        viewModel.mainState.observe(this, Observer {
            when(it){
                is MainState.NextActivity -> nextActivity()
                is MainState.ToastEvent -> showToast(this, it.text)
                is MainState.LoadingData -> setItems(it.items)
            }
        })
    }

    fun nextActivity(){
        startActivity(Intent(this, MainMVPActivity::class.java))
        finish()
    }
    fun setItems(items: List<String>){
        mAdapter.setItems(items)
    }

    override fun onClickListner(position: Int, tag: String) {
        viewModel.onAction(MainAction.ItemClicked(tag))
    }
}