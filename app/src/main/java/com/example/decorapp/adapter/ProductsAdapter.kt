package com.example.decorapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.decorapp.databinding.ProductLayoutItemBinding
import com.example.decorapp.model.ItemModel

class ProductsAdapter(val onProductClicked: (item: ItemModel) -> Unit): ListAdapter<ItemModel, ProductsAdapter.ItemViewHolder>(DiffCallback()) {


    private class DiffCallback : DiffUtil.ItemCallback<ItemModel>() {

        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel) =
            oldItem == newItem
    }

   inner class ItemViewHolder constructor(val binding: ProductLayoutItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val context = binding.root.context
        fun bind(item: ItemModel) {
         binding.itemCardView.background = ContextCompat.getDrawable(context, item.image)
            binding.layoutItemName.text = item.name
            binding.layoutItemTitle.text = item.title
            binding.itemCardView.setOnClickListener {
                onProductClicked(item)
            }

            binding.likeBtn.setOnClickListener {
                binding.likeBtn.isSelected = !binding.likeBtn.isSelected
            }
        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductLayoutItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    override fun getItemCount() = currentList.size

}