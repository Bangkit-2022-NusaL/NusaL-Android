package com.capstone.nusal.ui.kamus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.nusal.databinding.ActivityKamusDetailCategoryBinding

// Berisi kata2 dan foto
class KamusDetailCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKamusDetailCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKamusDetailCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // RecyclerView TBD
        // Implement searchView is possible
    }

    companion object {
        const val EXTRA_CATEGORY = "extra_category"
    }
}