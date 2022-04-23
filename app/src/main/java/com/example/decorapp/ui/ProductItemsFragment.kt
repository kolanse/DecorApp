package com.example.decorapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.decorapp.R
import com.example.decorapp.adapter.ProductsAdapter
import com.example.decorapp.databinding.FragmentProductItemsBinding
import com.example.decorapp.model.ItemModel
import com.example.decorapp.utils.SpringItemAnimator


class ProductItemsFragment(private val items: List<ItemModel>) : Fragment() {
    private lateinit var adapter: ProductsAdapter
    private var _binding: FragmentProductItemsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductItemsBinding.inflate(inflater, container, false)
        initAdapter()
        populateAdapter()

        return binding.root
    }


    private fun initAdapter() {
        adapter  = ProductsAdapter(::onProductClicked)
        binding.productsRv.adapter = adapter
    }

    private fun populateAdapter() {

        adapter.submitList(items)

    }


     private fun onProductClicked(item: ItemModel){
         val bundle = Bundle()
         bundle.putSerializable("DATA", item)
         findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment, bundle)
     }

}