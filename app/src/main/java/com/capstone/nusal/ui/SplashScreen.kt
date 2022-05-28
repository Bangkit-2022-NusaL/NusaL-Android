package com.capstone.nusal.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.capstone.nusal.R
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
        setContentView(R.layout.activity_splash_screen)

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
}