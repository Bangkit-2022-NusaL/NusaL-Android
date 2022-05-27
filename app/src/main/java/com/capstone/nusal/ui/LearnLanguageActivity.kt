package com.capstone.nusal.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.nusal.databinding.ActivityLearnLanguageBinding

class LearnLanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLearnLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView
    }
}