package com.example.decorapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.decorapp.adapter.ProductTabsAdapter
import com.example.decorapp.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setTabLayout()
        return binding.root
    }




    private fun setTabLayout() {

        val tabs = listOf(TRENDING, CHAIRS, DECORS)
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPager.adapter = ProductTabsAdapter(tabs, childFragmentManager, lifecycle)
        binding.tablayout.tabGravity = TabLayout.GRAVITY_START
        TabLayoutMediator(
            binding.tablayout,
            binding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position -> tab.text = tabs[position] }
        ).attach()


    }

    companion object {
        const val TRENDING = "Trending"
        const val CHAIRS = "Chairs"
        const val DECORS = "Decors"
    }


}