package com.capstone.nusal.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.nusal.data.CategoryModel
import com.capstone.nusal.data.LearnLanguageModel
import com.capstone.nusal.databinding.ItemCategoryBinding
import com.capstone.nusal.ui.learn.LearnLanguageAksaraActivity
import com.capstone.nusal.ui.learn.LearnLanguageDetailActivity

class LearnLanguageCategoryAdapter: ListAdapter<CategoryModel, LearnLanguageCategoryAdapter.LanguageCategoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageCategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguageCategoryViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }

    class LanguageCategoryViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CategoryModel) {
            Glide.with(itemView.context)
                .load(data.categoryImage)
                .into(binding.imgCategory)

            binding.tvCategoryName.text = data.categoryName

            itemView.setOnClickListener {
                val detailLearnLanguage = Intent(itemView.context, LearnLanguageDetailActivity::class.java)
                detailLearnLanguage.putExtra(LearnLanguageAksaraActivity.EXTRA_LANGUAGE, binding.tvCategoryName.text.toString())
                itemView.context.startActivity(detailLearnLanguage)
            }
        }
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CategoryModel>() {
            override fun areItemsTheSame(
                oldUser: CategoryModel,
                newUser: CategoryModel
            ): Boolean {
                return oldUser == newUser
            }

            override fun areContentsTheSame(
                oldUser: CategoryModel,
                newUser: CategoryModel
            ): Boolean {
                return oldUser.categoryName == newUser.categoryName
            }
        }
    }
}