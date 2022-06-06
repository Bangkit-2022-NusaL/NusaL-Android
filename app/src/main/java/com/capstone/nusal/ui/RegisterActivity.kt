package com.capstone.nusal.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.capstone.nusal.R
import com.capstone.nusal.data.Result
import com.capstone.nusal.databinding.ActivityRegisterBinding
import com.capstone.nusal.viewmodel.RegisterViewModel
import com.capstone.nusal.viewmodel.ViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

        supportActionBar?.hide()
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val registerViewModel: RegisterViewModel by viewModels {
            factory
        }

        val fieldRegisterName = binding.edtRegisterName
        val fieldRegisterEmail = binding.edtRegisterEmail
        val fieldRegisterPassword = binding.edtRegisterPassword

        fieldRegisterName.doOnTextChanged { text, start, before, count ->
            if(text.toString().isEmpty()) {
                binding.tilRegisterName.apply {
                    error = "Nama tidak boleh kosong"
                    isErrorEnabled = true
                }
            } else {
                binding.tilRegisterName.apply {
                    error = null
                    isErrorEnabled = false
                }
            }
        }

        fieldRegisterEmail.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(fieldRegisterEmail.isEmailComply) {
                    binding.tilRegisterEmail.apply {
                        error = null
                        isErrorEnabled = false
                    }
                } else {
                    binding.tilRegisterEmail.apply {
                        error = "Masukkan email dengan format benar"
                        isErrorEnabled = true
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        fieldRegisterPassword.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(fieldRegisterPassword.isPasswordComply) {
                    binding.tilRegisterPassword.apply {
                        error = null
                        isErrorEnabled = false
                    }
                } else {
                    binding.tilRegisterPassword.apply {
                        error = "Password minimal 8 karakter"
                        isErrorEnabled = true
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.btnRegister.setOnClickListener {
            val registerName = binding.edtRegisterName.text.toString()
            val registerEmail = binding.edtRegisterEmail.text.toString().lowercase()
            val registerPassword = binding.edtRegisterPassword.text.toString()

            if(registerName.isNotEmpty() && registerEmail.isNotEmpty() && registerPassword.isNotEmpty()) {
                registerViewModel.doRegister(registerName, registerEmail, registerPassword).observe(this) { result ->
                    if(result != null) {
                        when(result) {
                            is Result.Loading -> {
                                binding.pbRegisterLoading.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                binding.pbRegisterLoading.visibility = View.GONE
                                Toast.makeText(this@RegisterActivity, "Daftar berhasil, silahkan masuk", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            is Result.Error -> {
                                binding.pbRegisterLoading.visibility = View.GONE
                                Toast.makeText(this@RegisterActivity, "Email sudah terdaftar", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            } else {
                Toast.makeText(this@RegisterActivity, "Silahkan isi semua data", Toast.LENGTH_SHORT).show()
            }
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