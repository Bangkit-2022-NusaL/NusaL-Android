package com.capstone.nusal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.nusal.R
import com.capstone.nusal.data.CategoryModel
import com.capstone.nusal.databinding.ItemCategoryBinding

class CategoryAdapter: ListAdapter<CategoryModel, CategoryAdapter.CategoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)

        if(category != null) {
            holder.bind(category)
        }
    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CategoryModel) {
            Glide.with(itemView.context)
                .load(data.categoryImage) // TODO: Add placeholder
                .into(binding.imgCategory)

            binding.tvCategoryName.text = data.categoryName

            itemView.setOnClickListener {
                // To DetailCategory, intent with data, then proceed to take list of word/sentence
                // as per intent data. Like 'animals' then fetch animal image, animal word from strings.xml
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