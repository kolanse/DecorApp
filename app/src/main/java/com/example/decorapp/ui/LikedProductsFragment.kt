package com.example.decorapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnNextLayout
import com.example.decorapp.R
import com.example.decorapp.adapter.LikedProductsAdapter
import com.example.decorapp.databinding.FragmentLikedProductsBinding
import com.example.decorapp.utils.BottomMarginItemDecoration
import com.example.decorapp.utils.SpringItemAnimator
import com.example.decorapp.utils.favouriteDataDummy
import com.example.decorapp.utils.trendingDataDummy
import java.util.concurrent.TimeUnit

class LikedProductsFragment : Fragment() {

    private var _binding:FragmentLikedProductsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLikedProductsBinding.inflate(inflater, container, false)

         binding.likedProductsRv.apply {
              itemAnimator = SpringItemAnimator()
             addItemDecoration(
                 BottomMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.grid_2))
             )
             adapter = LikedProductsAdapter().apply {
                     doOnNextLayout {
                         startPostponedEnterTransition()
                     }




                 submitList(favouriteDataDummy)
             }

         }

        postponeEnterTransition(1000L, TimeUnit.MILLISECONDS)

        return binding.root

    }




}