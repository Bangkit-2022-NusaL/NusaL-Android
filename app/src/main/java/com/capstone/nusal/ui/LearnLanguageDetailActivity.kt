package com.capstone.nusal.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.nusal.R
import com.capstone.nusal.adapter.LearnLanguageAdapter
import com.capstone.nusal.data.LearnLanguageModel
import com.capstone.nusal.databinding.ActivityLearnLanguageDetailBinding

class LearnLanguageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLearnLanguageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnLanguageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // TODO: initContent based on intent.getExtraString language
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

        // TODO: Pindahkan onClick ke sini (Activity) (pakai callback). Buat ngasih info ke next activity buat load model
        learnLanguageAdapter.submitList(languageList)
    }
}