package com.example.decorapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.decorapp.databinding.LikedProductsItemBinding
import com.example.decorapp.databinding.ProductLayoutItemBinding
import com.example.decorapp.model.ItemModel


class LikedProductsAdapter(): ListAdapter<ItemModel, LikedProductsAdapter.ItemViewHolder>(DiffCallback()) {


    private class DiffCallback : DiffUtil.ItemCallback<ItemModel>() {

        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel) =
            oldItem == newItem
    }

    inner class ItemViewHolder constructor(val binding: LikedProductsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val context = binding.root.context
        fun bind(item: ItemModel) {
            binding.productImage.background = ContextCompat.getDrawable(context, item.image)
            binding.productDetails.text = item.name
            binding.productTitle.text = item.title

        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LikedProductsItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    override fun getItemCount() = currentList.size

}