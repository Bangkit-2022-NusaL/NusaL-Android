package com.capstone.nusal.ui.kamus

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.capstone.nusal.R
import com.capstone.nusal.databinding.ActivityKamusWordBinding

// Kata: Foto, kata Indonesia, kata daerah
class KamusWordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKamusWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKamusWordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}