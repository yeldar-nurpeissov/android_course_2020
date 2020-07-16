package com.example.myapplication.main.presentation.enterInfo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.main.presentation.enterInfo.AboutFragment
import com.example.myapplication.main.presentation.enterInfo.DetailFragment
import com.example.myapplication.main.presentation.enterInfo.FullNameFragment

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment){

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        0 to { FullNameFragment() },
        1 to { DetailFragment() },
        2 to { AboutFragment() }
    )

    override fun getItemCount(): Int = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}