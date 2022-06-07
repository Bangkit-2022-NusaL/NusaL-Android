package com.capstone.nusal.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capstone.nusal.data.SessionDataStore
import com.capstone.nusal.data.TokenHolder
import com.capstone.nusal.data.pref.OnboardingPref
import com.capstone.nusal.databinding.ActivitySplashScreenBinding
import com.capstone.nusal.viewmodel.datastore.SessionViewModel
import com.capstone.nusal.viewmodel.datastore.SessionViewModelFactory

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

        val session = SessionDataStore.getInstance(dataStore)
        val sessionViewModel = ViewModelProvider(this, SessionViewModelFactory(session))[SessionViewModel::class.java]

        val isFirstTime: Boolean

        sessionViewModel.getToken().observe(this) { token ->
            if(token.isNotEmpty()) {
                TokenHolder.token = token
            } else {
                TokenHolder.token = null
            }
        }

        val sharedPref = OnboardingPref(this)
        isFirstTime = sharedPref.isFirstTime()

        Handler(Looper.getMainLooper()).postDelayed({
            if(isFirstTime) {
                sharedPref.setPref(false)
                startActivity(Intent(this@SplashScreen, OnboardingActivity::class.java))
            } else {
                sessionViewModel.isLogin().observe(this) {
                    if (it != null) {
                        if (it) {
                            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                        } else {
                            startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
                        }
                    }
                }
            }
            finish()
        }, 2000L)
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}