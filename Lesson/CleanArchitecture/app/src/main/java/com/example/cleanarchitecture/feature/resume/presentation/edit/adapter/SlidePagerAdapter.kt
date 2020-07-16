package com.example.cleanarchitecture.feature.resume.presentation.edit.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cleanarchitecture.feature.resume.presentation.edit.fragments.FullNameFragment
import com.example.cleanarchitecture.feature.resume.presentation.edit.fragments.DetailsFragment
import com.example.cleanarchitecture.feature.resume.presentation.edit.fragments.AboutFragment

class SlidePagerAdapter(private val count: Int, fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = count

    override fun createFragment(position: Int): Fragment =
        when(position) {
            0 -> FullNameFragment()
            1 -> DetailsFragment()
            2 -> AboutFragment()
            else -> FullNameFragment()
        }

}