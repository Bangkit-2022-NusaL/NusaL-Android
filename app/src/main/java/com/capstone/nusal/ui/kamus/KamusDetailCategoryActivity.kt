package com.capstone.nusal.ui.kamus

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.capstone.nusal.R
import com.capstone.nusal.adapter.KamusDetailCategoryAdapter
import com.capstone.nusal.data.KamusDetailModel
import com.capstone.nusal.databinding.ActivityKamusDetailCategoryBinding

// Berisi kata2 dan foto
class KamusDetailCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKamusDetailCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKamusDetailCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val kamusList = mutableListOf<KamusDetailModel>()

        Glide.with(this)
            .load(intent?.getStringExtra(EXTRA_IMAGE).toString())
            .into(binding.imgKamusBahasa)

        when(intent.getStringExtra(EXTRA_CATEGORY)) {
            "Jawa" -> {
                val kamusTitle = resources.getStringArray(R.array.kamus_jawa_title)
                val kamusImage = resources.getStringArray(R.array.kamus_jawa_image)
                val kamusDesc = resources.getStringArray(R.array.kamus_jawa_desc)

                for(i in kamusTitle.indices) {
                    kamusList.add(KamusDetailModel(kamusTitle[i], kamusImage[i], kamusDesc[i]))
                }
            }
            "Sunda" -> {
                val kamusTitle = resources.getStringArray(R.array.kamus_sunda_title)
                val kamusImage = resources.getStringArray(R.array.kamus_sunda_image)
                val kamusDesc = resources.getStringArray(R.array.kamus_sunda_desc)

                for(i in kamusTitle.indices) {
                    kamusList.add(KamusDetailModel(kamusTitle[i], kamusImage[i], kamusDesc[i]))
                }
            }
        }

        binding.rvDetailCategory.layoutManager = GridLayoutManager(this, 2)
        val detailKamusAdapter = KamusDetailCategoryAdapter()
        binding.rvDetailCategory.adapter = detailKamusAdapter

        detailKamusAdapter.submitList(kamusList)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.svKamus

        searchView.setSearchableInfo((searchManager.getSearchableInfo(componentName)))
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchedKamusList = mutableListOf<KamusDetailModel>()

                for(i in kamusList) {
                    if(i.wordName.lowercase().contains(newText.toString().lowercase())) {
                        searchedKamusList.add(i)
                    }
                }

                detailKamusAdapter.submitList(searchedKamusList)
                return true
            }
        })
    }

    companion object {
        const val EXTRA_CATEGORY = "extra_category"
        const val EXTRA_IMAGE = "extra_image"
    }
}