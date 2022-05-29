package com.capstone.nusal.ui

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.nusal.R
import com.capstone.nusal.adapter.LearnLanguageAdapter
import com.capstone.nusal.data.LearnLanguageModel
import com.capstone.nusal.databinding.ActivityLearnLanguageBinding

class LearnLanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLearnLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initContent()
    }

    private fun initContent() {
        binding.rvBelajar.layoutManager = LinearLayoutManager(this)
        val learnLanguageAdapter = LearnLanguageAdapter()
        binding.rvBelajar.adapter = learnLanguageAdapter

        val aksaraName = resources.getStringArray(R.array.belajar_aksara_title)

        // still waiting for the image of the aksara, but if the image available just uncomment this line of code below, adapter, and model

//        val aksaraImage = resources.getStringArray(R.array.belajar_aksara_image)
        val languageList = ArrayList<LearnLanguageModel>()

        for (i in aksaraName.indices) {
            languageList.add(LearnLanguageModel(aksaraName[i]))
        }
        learnLanguageAdapter.submitList(languageList)
    }
}