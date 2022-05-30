package com.capstone.nusal.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.nusal.data.LearnLanguageModel
import com.capstone.nusal.databinding.ItemLearnLanguageBinding
import com.capstone.nusal.ui.learn.LearnLanguageAksaraActivity

class LearnLanguageDetailAdapter: ListAdapter<LearnLanguageModel, LearnLanguageDetailAdapter.LanguageViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding = ItemLearnLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val aksaraItem = getItem(position)

        if (aksaraItem != null) {
            holder.bind(aksaraItem)
        }

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(aksaraItem)}
    }

    class LanguageViewHolder(private val binding: ItemLearnLanguageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: LearnLanguageModel) {
//            Glide.with(itemView.context)
//                .load(data.aksaraImage)
//                .into(binding.imgAksara)

            binding.tvAksaraName.text = data.aksaraName
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: LearnLanguageModel)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LearnLanguageModel>() {
            override fun areItemsTheSame(
                oldUser: LearnLanguageModel,
                newUser: LearnLanguageModel
            ): Boolean {
                return oldUser == newUser
            }

            override fun areContentsTheSame(
                oldUser: LearnLanguageModel,
                newUser: LearnLanguageModel
            ): Boolean {
                return oldUser.aksaraName == newUser.aksaraName
            }
        }
    }
}

