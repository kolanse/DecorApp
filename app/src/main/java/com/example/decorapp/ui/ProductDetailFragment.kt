package com.example.decorapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.decorapp.*
import com.example.decorapp.adapter.CartImagesAdapter
import com.example.decorapp.databinding.FragmentLikedProductsBinding
import com.example.decorapp.databinding.FragmentProductDetailBinding
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.min


class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)


        handleProductQuantity()
        handleAddToCart()
        handleViewPager()
        handleBackButton()
        handleColorSelection()
        handleLikeButton()
        return binding.root
    }

    private fun handleLikeButton() {
        binding.likeBtn.setOnClickListener {
            binding.likeBtn.isSelected = !binding.likeBtn.isSelected
        }

    }

    private fun handleColorSelection() {
        var currentSelected = binding.selectColorOne
        binding.selectColorOne.setOnClickListener {
            currentSelected.reduce()
            (it as ShapeableImageView).increase()
            currentSelected = it
        }

        binding.selectColorTwo.setOnClickListener {
            currentSelected.reduce()
            (it as ShapeableImageView).increase()
            currentSelected = it
        }

        binding.selectColorThree.setOnClickListener {
            currentSelected.reduce()
            (it as ShapeableImageView).increase()
            currentSelected = it
        }
    }


    private fun handleProductQuantity() {
        binding.apply {
            addBtn.setOnClickListener {
                ((quantityTv.text.toString().toInt()) + 1).toString().also { quantityTv.text = it }
            }

            minusBtn.setOnClickListener {
                if (quantityTv.text.toString().toInt() == 1){
                    requireContext().showToast("Product Quantity cannot be less than 1")
                } else {
                    ((quantityTv.text.toString().toInt()) - 1).toString()
                        .also { quantityTv.text = it }

                }
            }
        }
    }

    private fun handleAddToCart() {
      binding.addToCartBtn.setOnClickListener {
          requireContext().showToast("Product added to cart")
      }
    }

    private fun handleViewPager() {


        binding.viewPager.setShowSideItems(70, 70)
     val imageList = listOf(R.drawable.decor_three_image, R.drawable.decor_three_image, R.drawable.chair_one_image )

        val adapter = CartImagesAdapter()
        adapter.submitList(imageList)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tablayout, binding.viewPager) { tab, position ->
        }.attach()
    }

    private fun handleBackButton() {

        binding.backButtonCard.setOnClickListener {
            findNavController().popBackStack()
        }

    }
}