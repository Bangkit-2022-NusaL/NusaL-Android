package com.capstone.nusal.ui

import android.os.Bundle
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.commit
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
                }
            } else {
                binding.tilForgotEmail.apply {
                    error = "Masukkan email dengan format benar"
                    isErrorEnabled = true
                }
            }
        }

        binding.edtForgotPassword1.doOnTextChanged { _, _, _, _ ->
            if(binding.edtForgotPassword1.isPasswordComply) {
                binding.tilForgotPassword1.apply {
                    error = null
                    isErrorEnabled = false
                }
            } else {
                binding.tilForgotPassword1.apply {
                    error = "Password minimal 8 karakter"
                    isErrorEnabled = true
                }
            }
        }

        binding.edtForgotPassword2.doOnTextChanged { text, _, _, _ ->
            if(text.toString() == binding.edtForgotPassword1.text.toString()) {
                binding.tilForgotPassword1.apply {
                    error = null
                    isErrorEnabled = false
                }
            } else {
                binding.tilForgotPassword2.apply {
                    error = "Password harus sama"
                    isErrorEnabled = true
                }
            }
        }

        binding.btnForgotPassword.setOnClickListener {
            val email = binding.edtForgotEmail.text.toString()
            val newPassword = binding.edtForgotPassword2.text.toString()


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
                                "Masuk gagal",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}