package com.example.decorapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.decorapp.R
import com.example.decorapp.databinding.CartImageItemBinding
import com.example.decorapp.model.ItemModel

class CartImagesAdapter(): ListAdapter<Int, CartImagesAdapter.ItemViewHolder>(DiffCallback()) {


    private class DiffCallback : DiffUtil.ItemCallback<Int>() {

        override fun areItemsTheSame(oldItem: Int, newItem: Int) =
           newItem == oldItem

        override fun areContentsTheSame(oldItem: Int, newItem: Int) =
            oldItem == newItem
    }

    inner class ItemViewHolder constructor(val binding: CartImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val context = binding.root.context
        fun bind(item: Int) {

            binding.itemCardView.background = ContextCompat.getDrawable(context, item)

        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CartImageItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    override fun getItemCount() = currentList.size

}