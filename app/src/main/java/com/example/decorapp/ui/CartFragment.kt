package com.example.decorapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnNextLayout
import com.example.decorapp.R
import com.example.decorapp.adapter.LikedProductsAdapter
import com.example.decorapp.databinding.FragmentCartBinding
import com.example.decorapp.utils.BottomMarginItemDecoration
import com.example.decorapp.utils.SpringItemAnimator
import com.example.decorapp.utils.cartDummy
import java.util.concurrent.TimeUnit


class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        binding.cartProductsRv.apply {
            itemAnimator = SpringItemAnimator()
            addItemDecoration(
                BottomMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.grid_2))
            )
            adapter = LikedProductsAdapter().apply {
                doOnNextLayout {
                    startPostponedEnterTransition()
                }




                submitList(cartDummy)
            }

        }

        postponeEnterTransition(1000L, TimeUnit.MILLISECONDS)


        return binding.root

    }


}