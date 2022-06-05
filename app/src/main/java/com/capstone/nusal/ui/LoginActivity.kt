package com.capstone.nusal.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.nusal.data.Result
import com.capstone.nusal.data.SessionDataStore
import com.capstone.nusal.databinding.ActivityLoginBinding
import com.capstone.nusal.viewmodel.LoginViewModel
import com.capstone.nusal.viewmodel.ViewModelFactory
import com.capstone.nusal.viewmodel.datastore.SessionViewModel
import com.capstone.nusal.viewmodel.datastore.SessionViewModelFactory

internal val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

        val session = SessionDataStore.getInstance(dataStore)
        val sessionViewModel = ViewModelProvider(this, SessionViewModelFactory(session))[SessionViewModel::class.java]

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val loginViewModel: LoginViewModel by viewModels {
            factory
        }

        val fieldLoginEmail = binding.edtLoginEmail
        val fieldLoginPassword = binding.edtLoginPassword

        fieldLoginEmail.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(fieldLoginEmail.isEmailComply) {
                    binding.tilLoginEmail.apply {
                        error = null
                        isErrorEnabled = false
                    }
                } else {
                    binding.tilLoginEmail.apply {
                        error = "Masukkan email dengan format benar"
                        isErrorEnabled = true
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        fieldLoginPassword.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(fieldLoginPassword.isPasswordComply) {
                    binding.tilLoginPassword.apply {
                        error = null
                        isErrorEnabled = false
                    }
                } else {
                    binding.tilLoginPassword.apply {
                        error = "Password harus minimal 8 karakter"
                        isErrorEnabled = true
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.btnLogin.setOnClickListener {
            val loginEmail = binding.edtLoginEmail.text.toString().lowercase()
            val loginPassword = binding.edtLoginPassword.text.toString()

            if(loginEmail.isNotEmpty() && loginPassword.isNotEmpty()) {
                loginViewModel.userLogin(loginEmail, loginPassword).observe(this) { result ->
                    if(result != null) {
                        when(result) {
                            is Result.Loading -> {
                                binding.pbLoginLoading.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                binding.pbLoginLoading.visibility = View.GONE
                                val response = result.data
                                sessionViewModel.saveSession(response.token.toString())

                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                finish()
                                Toast.makeText(this@LoginActivity, "Berhasil masuk", Toast.LENGTH_SHORT).show()
                            }
                            is Result.Error -> {
                                binding.pbLoginLoading.visibility = View.GONE
                                Toast.makeText(this@LoginActivity, "Masuk gagal", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            } else {
                Toast.makeText(this@LoginActivity, "Silahkan isi semua data untuk masuk", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnToRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        binding.tvForgotPassword.setOnClickListener {
            // startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }
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