package com.example.cleanarchitecture.feature.resume.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cleanarchitecture.feature.resume.presentation.fragments.SliderFirstScreenFragment
import com.example.cleanarchitecture.feature.resume.presentation.fragments.SliderSecondScreenFragment
import com.example.cleanarchitecture.feature.resume.presentation.fragments.SliderThirdScreenFragment

class SlidePagerAdapter(private val count: Int, fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = count

    override fun createFragment(position: Int): Fragment =
        when(position) {
            0 -> SliderFirstScreenFragment()
            1 -> SliderSecondScreenFragment()
            2 -> SliderThirdScreenFragment()
            else -> SliderFirstScreenFragment()
        }

}