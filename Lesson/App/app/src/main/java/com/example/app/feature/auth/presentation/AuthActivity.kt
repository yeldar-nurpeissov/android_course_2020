package com.example.app.feature.auth.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.R
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(com.example.app.feature.auth.domain.fragments.NameFragment(), "Name")
        adapter.addFragment(com.example.app.feature.auth.domain.fragments.DateHWFragment(), "Date")
        adapter.addFragment(com.example.app.feature.auth.domain.fragments.AboutFragment(), "About")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}
