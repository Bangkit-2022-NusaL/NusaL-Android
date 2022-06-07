package com.capstone.nusal.ui.learn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.nusal.R
import com.capstone.nusal.adapter.LearnLanguageCategoryAdapter
import com.capstone.nusal.data.CategoryModel
import com.capstone.nusal.databinding.ActivityLearnLanguageCategoryBinding

class LearnLanguageCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLearnLanguageCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnLanguageCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.rvLanguageCategory.layoutManager = LinearLayoutManager(this)
        val rvAdapter = LearnLanguageCategoryAdapter()
        binding.rvLanguageCategory.adapter = rvAdapter

        val languageChoice = resources.getStringArray(R.array.language_choice)
        val languageImage = resources.getStringArray(R.array.language_image)

        val listLanguage = arrayListOf<CategoryModel>()

        for(i in languageChoice.indices) {
            listLanguage.add(CategoryModel(languageChoice[i], languageImage[i]))
        }

        rvAdapter.submitList(listLanguage)
    }
}