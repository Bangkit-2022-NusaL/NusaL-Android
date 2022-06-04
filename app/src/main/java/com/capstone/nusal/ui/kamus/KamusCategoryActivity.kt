package com.capstone.nusal.ui.kamus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.nusal.R
import com.capstone.nusal.adapter.KamusCategoryAdapter
import com.capstone.nusal.data.CategoryModel
import com.capstone.nusal.databinding.ActivityKamusCategoryBinding

class KamusCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKamusCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKamusCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.rvCategory.layoutManager = LinearLayoutManager(this)
        val rvAdapter = KamusCategoryAdapter()
        binding.rvCategory.adapter = rvAdapter

        val languageChoice = resources.getStringArray(R.array.language_choice)
        val languageImage = resources.getStringArray(R.array.language_image)

        val listLanguage = arrayListOf<CategoryModel>()

        for(i in languageChoice.indices) {
            listLanguage.add(CategoryModel(languageChoice[i], languageImage[i]))
        }

        rvAdapter.submitList(listLanguage)
    }
}