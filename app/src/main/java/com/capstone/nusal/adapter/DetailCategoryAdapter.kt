package com.capstone.nusal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.nusal.data.CategoryModel
import com.capstone.nusal.databinding.ItemDetailCategoryBinding

// Ini dipakai di KamusDetailCategory
class DetailCategoryAdapter: ListAdapter<CategoryModel, DetailCategoryAdapter.DetailCategoryViewHolder>(
    DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailCategoryAdapter.DetailCategoryViewHolder {
        val binding = ItemDetailCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailCategoryViewHolder, position: Int) {
        val category = getItem(position)

        if(category != null) {
            holder.bind(category)
        }
    }

    class DetailCategoryViewHolder(private val binding: ItemDetailCategoryBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CategoryModel) {
            Glide.with(itemView.context)
                .load(data.categoryImage) // TODO: Add placeholder
                .into(binding.imgItemDetailCategory)

            binding.tvItemDetailCategory.text = data.categoryName

            itemView.setOnClickListener {
                // To
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CategoryModel>() {
            override fun areItemsTheSame(oldUser: CategoryModel, newUser: CategoryModel): Boolean {
                return oldUser == newUser
            }

            override fun areContentsTheSame(oldUser: CategoryModel, newUser: CategoryModel): Boolean {
                return oldUser.categoryName == newUser.categoryName
            }
        }
    }
}