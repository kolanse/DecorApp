package com.example.decorapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.decorapp.ui.HomeFragment
import com.example.decorapp.ui.ProductItemsFragment
import com.example.decorapp.utils.chairDataDummy
import com.example.decorapp.utils.decorDataDummy
import com.example.decorapp.utils.trendingDataDummy

class ProductTabsAdapter(private val tabs: List<String>, fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = tabs.size


    override fun createFragment(position: Int): Fragment {
        return when (tabs[position]) {
            HomeFragment.TRENDING-> ProductItemsFragment(trendingDataDummy)
            HomeFragment.CHAIRS-> ProductItemsFragment(chairDataDummy)
            HomeFragment.DECORS -> ProductItemsFragment(decorDataDummy)
            else -> ProductItemsFragment(trendingDataDummy)
        }
    }
}