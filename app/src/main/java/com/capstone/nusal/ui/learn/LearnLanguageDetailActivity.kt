package com.capstone.nusal.ui.learn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.nusal.R
import com.capstone.nusal.adapter.LearnLanguageDetailAdapter
import com.capstone.nusal.data.LearnLanguageModel
import com.capstone.nusal.databinding.ActivityLearnLanguageDetailBinding

class LearnLanguageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLearnLanguageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnLanguageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val extraLanguage = intent.getStringExtra(LearnLanguageAksaraActivity.EXTRA_LANGUAGE)
        initContent(extraLanguage.toString())
    }

    private fun initContent(extraLanguage: String) {
        binding.rvBelajar.layoutManager = GridLayoutManager(this, 2)
        val learnLanguageAdapter = LearnLanguageDetailAdapter()
        binding.rvBelajar.adapter = learnLanguageAdapter

        learnLanguageAdapter.setOnItemClickCallback(object:
            LearnLanguageDetailAdapter.OnItemClickCallback {
                override fun onItemClicked(data: LearnLanguageModel) {
                    val detailLearnLanguage = Intent(this@LearnLanguageDetailActivity, LearnLanguageAksaraActivity::class.java)
                    detailLearnLanguage.putExtra(LearnLanguageAksaraActivity.EXTRA_AKSARA, data.aksaraName)
                    // detailLearnLanguage.putExtra(LearnLanguageAksaraActivity.EXTRA_AKSARA_IMG, data.aksaraImg)

                    detailLearnLanguage.putExtra(LearnLanguageAksaraActivity.EXTRA_LANGUAGE, extraLanguage)
                    startActivity(detailLearnLanguage)
                }
            })

        var aksaraNameList = arrayOf<String>()
        // var aksaraImgList = arrayOf<String>()

        if(extraLanguage == "Jawa") {
            aksaraNameList = resources.getStringArray(R.array.belajar_aksara_jawa_title)
            // aksaraImgList = resources.getStringArray(R.array.belajar_aksara_jawa_image)
        } else if (extraLanguage == "Sunda"){
            aksaraNameList = resources.getStringArray(R.array.belajar_aksara_sunda_title)
            // aksaraImgList = resources.getStringArray(R.array.belajar_aksara_sunda_image)
        }

        val languageList = ArrayList<LearnLanguageModel>()

        for (i in aksaraNameList.indices) {
            languageList.add(LearnLanguageModel(aksaraNameList[i])) // Not yet image
        }

        learnLanguageAdapter.submitList(languageList)
    }
}