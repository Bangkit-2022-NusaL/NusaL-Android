package com.capstone.nusal.ui

import android.os.Build
import android.os.Bundle
import android.text.TextWatcher
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.capstone.nusal.R
import com.capstone.nusal.data.Result
import com.capstone.nusal.databinding.ActivityForgotPasswordBinding
import com.capstone.nusal.ui.fragment.SuccessResetPasswordFragment
import com.capstone.nusal.viewmodel.ForgotPasswordViewModel
import com.capstone.nusal.viewmodel.ViewModelFactory

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

        val factory = ViewModelFactory.getInstance(this)
        val forgotPasswordViewModel: ForgotPasswordViewModel by viewModels {
            factory
        }

        // Email and Password Validation
        binding.edtForgotEmail.doOnTextChanged { _, _, _, _ ->
            if(binding.edtForgotEmail.isEmailComply) {
                binding.tilForgotEmail.apply {
                    error = null
                    isErrorEnabled = false
                    binding.btnForgotPassword.isEnabled = true
                }
            } else {
                binding.tilForgotEmail.apply {
                    error = "Masukkan email dengan format benar"
                    isErrorEnabled = true
                    binding.btnForgotPassword.isEnabled = false
                }
            }
        }

        binding.edtForgotPassword1.doOnTextChanged { _, _, _, _ ->
            if(binding.edtForgotPassword1.isPasswordComply) {
                binding.tilForgotPassword1.apply {
                    error = null
                    isErrorEnabled = false
                    binding.btnForgotPassword.isEnabled = true
                }
            } else {
                binding.tilForgotPassword1.apply {
                    error = "Password minimal 8 karakter"
                    isErrorEnabled = true
                    binding.btnForgotPassword.isEnabled = false
                }
            }
        }

        binding.edtForgotPassword2.doOnTextChanged { text, _, _, _ ->
            if(text.toString() == binding.edtForgotPassword1.text.toString()) {
                binding.tilForgotPassword2.apply {
                    error = null
                    isErrorEnabled = false
                    binding.btnForgotPassword.isEnabled = true
                }
            } else {
                binding.tilForgotPassword2.apply {
                    error = "Password harus sama"
                    isErrorEnabled = true
                    binding.btnForgotPassword.isEnabled = false
                }
            }
        }

        binding.btnForgotPassword.setOnClickListener {
            val email = binding.edtForgotEmail.text.toString()
            val newPassword = binding.edtForgotPassword2.text.toString()

            if(email.isNotEmpty() && newPassword.isNotEmpty()) {
                forgotPasswordViewModel.resetPassword(email, newPassword).observe(this) { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> {
                                // Loading start
                            }
                            is Result.Success -> {
                                // Loading stop
                                val response = result.data

                                // Start fragment transaction
                                val mFragmentManager = supportFragmentManager
                                val mSuccessFragment = SuccessResetPasswordFragment()

                                mFragmentManager.commit {
                                    add(
                                        binding.frameContainerForgotPassword.id,
                                        mSuccessFragment,
                                        SuccessResetPasswordFragment::class.java.simpleName
                                    )
                                }
                            }
                            is Result.Error -> {
                                // Loading Stop
                                Toast.makeText(
                                    this@ForgotPasswordActivity,
                                    "Gagal mengirim rekues",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            } else {
                Toast.makeText(this@ForgotPasswordActivity, "Isi semua kolom", Toast.LENGTH_SHORT).show()
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