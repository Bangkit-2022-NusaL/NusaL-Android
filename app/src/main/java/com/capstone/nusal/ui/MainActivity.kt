package com.capstone.nusal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.capstone.nusal.R
import com.capstone.nusal.data.SessionDataStore
import com.capstone.nusal.data.TokenHolder
import com.capstone.nusal.databinding.ActivityMainBinding
import com.capstone.nusal.viewmodel.ViewModelFactory
import com.capstone.nusal.viewmodel.datastore.SessionViewModel
import com.capstone.nusal.viewmodel.datastore.SessionViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val session = SessionDataStore.getInstance(dataStore)
        val sessionViewModel = ViewModelProvider(this, SessionViewModelFactory(session))[SessionViewModel::class.java]

        // Note: Pindahkan ke splashscreen?
        sessionViewModel.getToken().observe(this) { token ->
            if(token.isNotEmpty()) {
                TokenHolder.token = token
            } else {
                TokenHolder.token = null
            }
        }

        sessionViewModel.isLogin().observe(this) { isLogin ->
            if(isLogin) {
                if(TokenHolder.token != null) {
                    // continue
                }
            } else {
                TokenHolder.token = null
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }

        // TODO: Logout
    }
}