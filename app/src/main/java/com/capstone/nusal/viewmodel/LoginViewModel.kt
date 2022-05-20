package com.capstone.nusal.viewmodel

import androidx.lifecycle.ViewModel
import com.capstone.nusal.data.NusaRepository

class LoginViewModel(private val  nusaRepository: NusaRepository): ViewModel() {

    fun userLogin(email: String, password: String) = nusaRepository.login(email, password)
}