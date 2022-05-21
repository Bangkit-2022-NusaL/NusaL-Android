package com.capstone.nusal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        // TODO: Buat customView untuk email dan password

        binding.btnLogin.setOnClickListener {
            val loginEmail = binding.edtLoginEmail.text.toString()
            val loginPassword = binding.edtLoginPassword.text.toString()

            if(loginEmail.isNotEmpty() && loginPassword.isNotEmpty()) {
                loginViewModel.userLogin(loginEmail, loginPassword).observe(this) { result ->
                    if(result != null) {
                        when(result) {
                            is Result.Loading -> {
                                // Loading start
                            }
                            is Result.Success -> {
                                // Loading stop
                                // Simpan token

                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                Toast.makeText(this@LoginActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            is Result.Error -> {
                                // Loading stop
                                Toast.makeText(this@LoginActivity, "Login gagal", Toast.LENGTH_SHORT).show()
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