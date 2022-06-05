package com.capstone.nusal.viewmodel

import androidx.lifecycle.ViewModel
import com.capstone.nusal.data.NusaRepository

class ForgotPasswordViewModel(private val nusaRepository: NusaRepository): ViewModel() {

    fun resetPassword(email: String, newPassword: String) = nusaRepository.resetPassword(email, newPassword)
}