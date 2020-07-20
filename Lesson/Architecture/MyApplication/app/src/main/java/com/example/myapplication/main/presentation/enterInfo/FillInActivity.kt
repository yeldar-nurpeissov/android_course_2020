package com.example.myapplication.main.presentation.enterInfo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.main.presentation.enterInfo.adapter.ViewPagerAdapter
import com.example.myapplication.main.presentation.detailOfUser.DetailActivity
import com.example.myapplication.main.presentation.detailOfUser.EDIT_ACTION
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FillInActivity: AppCompatActivity() {

    private val viewModel: FillInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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