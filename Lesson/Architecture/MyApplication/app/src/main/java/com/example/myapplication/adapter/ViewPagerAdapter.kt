package com.example.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.presentation.AboutFragment
import com.example.myapplication.presentation.DetailFragment
import com.example.myapplication.presentation.NameFragment

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment){

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        0 to { NameFragment() },
        1 to { DetailFragment() },
        2 to {AboutFragment()}
    )

    override fun getItemCount(): Int = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}