package com.capstone.nusal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import com.capstone.nusal.data.Result
import com.capstone.nusal.databinding.ActivityLoginBinding
import com.capstone.nusal.viewmodel.LoginViewModel
import com.capstone.nusal.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                    binding.tilLoginPassword.error = null
                    binding.tilLoginEmail.isErrorEnabled = false
                } else {
                    binding.tilLoginPassword.error = "Password harus minimal 8 karakter"
                    binding.tilLoginEmail.isErrorEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        // TODO: try to observe once (not observe every onclicklistener, resulting multiple observer
//        loginViewModel.observeLoginResult().observe(this) { result ->
//            if(result != null) {
//                when(result) {
//                    is Result.Loading -> {
//                        // Show Loading
//                    }
//                    is Result.Success -> {
//                        // Stop loading
//                        // Save token
//
//                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//                        finish()
//                        Toast.makeText(this@LoginActivity, "Berhasil masuk", Toast.LENGTH_SHORT).show()
//                    }
//                    is Result.Error -> {
//                        // Stop loading
//                        Toast.makeText(this@LoginActivity, "Masuk gagal", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }

        binding.btnLogin.setOnClickListener {
            val loginEmail = binding.edtLoginEmail.text.toString()
            val loginPassword = binding.edtLoginPassword.text.toString()

            if(loginEmail.isNotEmpty() && loginPassword.isNotEmpty()) {
//                loginViewModel.userLogin(loginEmail, loginPassword)
                loginViewModel.userLogin(loginEmail, loginPassword).observe(this) { result ->
                    if(result != null) {
                        when(result) {
                            is Result.Loading -> {
                                // Show Loading
                            }
                            is Result.Success -> {
                                // Stop loading
                                // Save token

                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                finish()
                                Toast.makeText(this@LoginActivity, "Berhasil masuk", Toast.LENGTH_SHORT).show()
                            }
                            is Result.Error -> {
                                // Stop loading
                                Toast.makeText(this@LoginActivity, "Masuk gagal", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }

        binding.btnToRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }
}