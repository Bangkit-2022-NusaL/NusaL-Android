package com.capstone.nusal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.nusal.R

// LanguageCategory - Isi bahasa, Jawa, Sunda
class LearnLanguageCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_language_category)

        // Kalau mau Intent, kirim sekalian extra String
        // sesuai bahasa, kaya Jawa, Sunda.
    }
}