package com.capstone.nusal.ui.kamus

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.nusal.adapter.KamusDetailCategoryAdapter
import com.capstone.nusal.databinding.ActivityKamusDetailCategoryBinding

// Berisi kata2 dan foto
class KamusDetailCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKamusDetailCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKamusDetailCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        when(intent.getStringExtra(EXTRA_CATEGORY)) {
            "Jawa" -> {
                // Get array
            }
            "Sunda" -> {
                // Get array
            }
        }

        binding.rvDetailCategory.layoutManager = GridLayoutManager(this, 2)
        val detailKamusAdapter = KamusDetailCategoryAdapter()
        binding.rvDetailCategory.adapter = detailKamusAdapter

        // detailKamusAdapter.submitList()

        // Bikin SearchView disini
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.svKamus

        searchView.setSearchableInfo((searchManager.getSearchableInfo(componentName)))
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Create Local List
                // Loop, if each item contains newText.lowercase(), add to list
                // Submit list
                return true
            }

        })
    }

    companion object {
        const val EXTRA_CATEGORY = "extra_category"
    }
}