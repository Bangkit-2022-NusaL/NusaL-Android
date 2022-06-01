package com.capstone.nusal.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capstone.nusal.R
import com.capstone.nusal.data.SessionDataStore
import com.capstone.nusal.data.TokenHolder
import com.capstone.nusal.databinding.ActivityMainBinding
import com.capstone.nusal.ui.learn.LearnLanguageCategoryActivity
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
//            sessionViewModel.clearSession()
            // Add AlertDialog
            val view = View.inflate(this@MainActivity, R.layout.dialog_logout, null)

            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            val cancel : Button = view.findViewById(R.id.btn_cancel)
            val yes : Button = view.findViewById(R.id.btn_yes)


            cancel.setOnClickListener {
                dialog.hide()
            }

            yes.setOnClickListener {
                sessionViewModel.clearSession()
            }

        }

        binding.cardBelajar.setOnClickListener {
            startActivity(Intent(this@MainActivity, LearnLanguageCategoryActivity::class.java))
        }


//
//        binding.cardKamus.setOnClickListener {
//            startActivity(Intent(this@MainActivity, KamusCategoryActivity::class.java))
    }

    // Additional notes :
    // Tingkatan activity
    // Kamus pages:  KamusCategory -> KamusDetail (word and pict) -> KamusWord

    // Learn pages: LanguageCategory(bahasa) -> LanguageDetailCategory -> LanguageAksara
}

