package com.capstone.nusal.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capstone.nusal.data.SessionDataStore
import com.capstone.nusal.data.TokenHolder
import com.capstone.nusal.databinding.ActivityMainBinding
import com.capstone.nusal.viewmodel.datastore.SessionViewModel
import com.capstone.nusal.viewmodel.datastore.SessionViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val session = SessionDataStore.getInstance(dataStore)
        val sessionViewModel =
            ViewModelProvider(this, SessionViewModelFactory(session))[SessionViewModel::class.java]

        sessionViewModel.getToken().observe(this) { token ->
            if (token.isNotEmpty()) {
                TokenHolder.token = token
            } else {
                TokenHolder.token = null
            }
        }

        sessionViewModel.isLogin().observe(this) { isLogin ->
            if (isLogin) {
                if (TokenHolder.token != null) {
                    // continue
                }
            } else {
                TokenHolder.token = null
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }

        // TODO: Logout

        binding.btnLogout.setOnClickListener {
            sessionViewModel.clearSession()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }

//        binding.cardBelajar.setOnClickListener {
//            startActivity(Intent(this@MainActivity, LearnLanguageCategoryActivity::class.java))
//        }
//
//        binding.cardKamus.setOnClickListener {
//            startActivity(Intent(this@MainActivity, KamusLanguageActivity::class.java))
    }

    // Additional notes :
    // Tingkatan activity
    // Kamus pages: KamusLanguage (pilih bahasa) -> KamusCategory -> KamusDetail (word and pict) -> KamusWord

    // Learn pages: LanguageCategory(bahasa) -> LanguageDetailCategory -> LanguageAksara
}

