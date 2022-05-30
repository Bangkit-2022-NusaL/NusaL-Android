package com.capstone.nusal.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.nusal.data.CategoryModel
import com.capstone.nusal.databinding.ItemCategoryBinding
import com.capstone.nusal.ui.kamus.KamusDetailCategoryActivity

// Ini dipakai di KamusCategoryActivity
class KamusCategoryAdapter: ListAdapter<CategoryModel, KamusCategoryAdapter.CategoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KamusCategoryAdapter.CategoryViewHolder {
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
                val toDetailCategory = Intent(itemView.context, KamusDetailCategoryActivity::class.java)
                toDetailCategory.putExtra(KamusDetailCategoryActivity.EXTRA_CATEGORY, data.categoryName)
                itemView.context.startActivity(toDetailCategory)
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