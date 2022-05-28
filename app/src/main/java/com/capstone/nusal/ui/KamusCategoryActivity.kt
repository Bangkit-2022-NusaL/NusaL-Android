package com.capstone.nusal.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.nusal.R
import com.capstone.nusal.adapter.CategoryAdapter
import com.capstone.nusal.data.CategoryModel
import com.capstone.nusal.databinding.ActivityKamusCategoryBinding

// Kategori Kamus
class KamusCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKamusCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKamusCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryTitle = resources.getStringArray(R.array.kamus_kategori_title)
        val categoryImage = resources.getStringArray(R.array.kamus_kategori_image)

        val categoryList = ArrayList<CategoryModel>()

        for(i in categoryTitle.indices) {
            categoryList.add(CategoryModel(categoryTitle[i], categoryImage[i]))
        }

        binding.rvCategory.layoutManager = LinearLayoutManager(this)
        val categoryAdapter = CategoryAdapter()
        binding.rvCategory.adapter = categoryAdapter

        categoryAdapter.submitList(categoryList)
    }
}