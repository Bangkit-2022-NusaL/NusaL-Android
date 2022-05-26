package com.capstone.nusal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.nusal.R
import com.capstone.nusal.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get array data from strings.xml (category name and image link)
        // Instantiate recyclerview, layoutmanager, adapter.
    }
}