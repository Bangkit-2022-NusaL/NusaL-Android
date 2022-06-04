package com.capstone.nusal.ui.kamus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.nusal.databinding.ActivityKamusWordBinding

class KamusWordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKamusWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKamusWordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    companion object {
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_MEANING = "extra_meaning"
    }
}