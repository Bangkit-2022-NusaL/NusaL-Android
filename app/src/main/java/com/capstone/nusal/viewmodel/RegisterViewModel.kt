package com.capstone.nusal.viewmodel

import androidx.lifecycle.ViewModel
import com.capstone.nusal.data.NusaRepository

class RegisterViewModel(private val nusaRepository: NusaRepository): ViewModel() {

    fun doRegister(name: String, email: String, password: String) = nusaRepository.register(name, email, password)
}