package com.capstone.nusal.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.nusal.data.KamusDetailModel
import com.capstone.nusal.databinding.ItemKamusDetailCategoryBinding
import com.capstone.nusal.ui.kamus.KamusWordActivity
import com.capstone.nusal.ui.kamus.KamusWordActivity.Companion.EXTRA_IMAGE
import com.capstone.nusal.ui.kamus.KamusWordActivity.Companion.EXTRA_MEANING
import com.capstone.nusal.ui.kamus.KamusWordActivity.Companion.EXTRA_TITLE

class KamusDetailCategoryAdapter: ListAdapter<KamusDetailModel, KamusDetailCategoryAdapter.CategoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding = ItemKamusDetailCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)

        if(category != null) {
            holder.bind(category)
        }
    }

    class CategoryViewHolder(private val binding: ItemKamusDetailCategoryBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: KamusDetailModel) {
            Glide.with(itemView.context)
                .load(data.wordImageUrl)
                .circleCrop()
                .into(binding.imgItemDetailCategory)

            binding.tvItemDetailCategory.text = data.wordName

            itemView.setOnClickListener {
                val toWordCategory = Intent(itemView.context, KamusWordActivity::class.java)

                toWordCategory.putExtra(EXTRA_IMAGE, data.wordImageUrl)
                toWordCategory.putExtra(EXTRA_TITLE, data.wordName)
                toWordCategory.putExtra(EXTRA_MEANING, data.wordDescription)

                itemView.context.startActivity(toWordCategory)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<KamusDetailModel>() {
            override fun areItemsTheSame(oldUser: KamusDetailModel, newUser: KamusDetailModel): Boolean {
                return oldUser == newUser
            }

            override fun areContentsTheSame(oldUser: KamusDetailModel, newUser: KamusDetailModel): Boolean {
                return oldUser.wordName == newUser.wordName
            }
        }
    }
}