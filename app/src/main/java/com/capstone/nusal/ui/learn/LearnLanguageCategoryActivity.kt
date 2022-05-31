package com.capstone.nusal.ui.learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.nusal.R
import com.capstone.nusal.adapter.KamusCategoryAdapter
import com.capstone.nusal.adapter.LearnLanguageCategoryAdapter
import com.capstone.nusal.data.CategoryModel
import com.capstone.nusal.databinding.ActivityLearnLanguageCategoryBinding

// LanguageCategory - Isi bahasa, Jawa, Sunda
class LearnLanguageCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLearnLanguageCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnLanguageCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.rvLanguageCategory.layoutManager = GridLayoutManager(this, 2)
        val rvAdapter = LearnLanguageCategoryAdapter()
        binding.rvLanguageCategory.adapter = rvAdapter

        val languageChoice = resources.getStringArray(R.array.language_choice)
        // val languageImage = resources.getStringArray(R.array.language_image)

        val listLanguage = arrayListOf<CategoryModel>()

        for(i in languageChoice.indices) {
            listLanguage.add(CategoryModel(languageChoice[i], "PLACEHOLDER"))
        }

        rvAdapter.submitList(listLanguage)
    }
}