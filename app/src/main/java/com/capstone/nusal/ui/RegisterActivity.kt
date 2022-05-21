package com.capstone.nusal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
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

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val registerViewModel: RegisterViewModel by viewModels {
            factory
        }

        binding.btnRegister.setOnClickListener {
            val registerName = binding.edtRegisterName.text.toString()
            val registerEmail = binding.edtRegisterEmail.text.toString()
            val registerPassword = binding.edtRegisterPassword.text.toString()

            if(registerName.isNotEmpty() && registerEmail.isNotEmpty() && registerPassword.isNotEmpty()) {
                registerViewModel.doRegister(registerName, registerEmail, registerPassword).observe(this) { result ->
                    if(result != null) {
                        when(result) {
                            is Result.Loading -> {
                                // Loading start
                            }
                            is Result.Success -> {
                                // Loading stop

                                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                                Toast.makeText(this@RegisterActivity, "Daftar berhasil, silahkan masuk", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            is Result.Error -> {
                                // Loading stop
                                Toast.makeText(this@RegisterActivity, "Daftar gagal", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}