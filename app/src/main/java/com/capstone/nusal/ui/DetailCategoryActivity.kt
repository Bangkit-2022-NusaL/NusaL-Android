package com.capstone.nusal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.nusal.R
import com.capstone.nusal.databinding.ActivityDetailCategoryBinding

class DetailCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView first? then submitList depending on intent?

        // Get Intent
        when(intent.getStringExtra(EXTRA_CATEGORY)) {
            "Tumbuhan" -> {
                // fetch tumbuhan strings and submit list
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