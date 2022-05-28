package com.capstone.nusal.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.nusal.databinding.ActivityKamusDetailCategoryBinding

// Detail kategori, berisi contoh kata (kucing, anjing, sapi, dsb.)
class KamusDetailCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKamusDetailCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKamusDetailCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView first? then submitList depending on intent?

        // Get Intent
        when(intent.getStringExtra(EXTRA_CATEGORY)) {
            "Tumbuhan" -> {
                // fetch tumbuhan strings, AND also take its all description (for intent). and submit list
            }
            "Hewan" -> {
                // fetch hewan strings and submit list
            }
        }
    }

    companion object {
        const val EXTRA_CATEGORY = "extra_category"
    }
}