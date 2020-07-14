package com.example.myapplication.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.adapter.ViewPagerAdapter
import com.example.myapplication.core.DependencyInjection
import com.example.myapplication.presentation.viewmodel.EntryViewModel
import com.example.myapplication.presentation.viewmodel.GetDetailViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity1: AppCompatActivity() {
    private var di: DependencyInjection?= null

    private val viewModel: EntryViewModel by lazy {
        di!!.getExistViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        di = DependencyInjection.getInstance(this)

        val edit = intent.getBooleanExtra(EDIT_ACTION, false)
        if (!edit) {
            observeViewModel()
        }
        viewPager2.adapter = ViewPagerAdapter(this)
    }

    private fun observeViewModel() {
        viewModel.getExistEvent.observe(this, Observer {
            if (it.boolean){
                startActivity(Intent(this, DetailActivity::class.java))
            }
        })
    }


}