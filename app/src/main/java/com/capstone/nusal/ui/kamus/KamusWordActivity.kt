package com.capstone.nusal.ui.kamus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.nusal.databinding.ActivityKamusWordBinding

class KamusWordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKamusWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKamusWordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val wordImage = intent.getStringExtra(EXTRA_IMAGE)
        val wordTitle = intent.getStringExtra(EXTRA_TITLE)
        val wordMeaning = intent.getStringExtra(EXTRA_MEANING)

        Glide.with(this)
            .load(wordImage)
            .circleCrop()
            .into(binding.imgWord)

        binding.tvWordTitle.text = wordTitle
        binding.tvWordMeaning.text = wordMeaning

        binding.btnWordBack.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_MEANING = "extra_meaning"
    }
}