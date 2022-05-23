package com.capstone.nusal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.nusal.data.NusaRepository
import com.capstone.nusal.data.Result
import com.capstone.nusal.data.remote.LoginResponse

class LoginViewModel(private val  nusaRepository: NusaRepository): ViewModel() {

    // TODO: Trial observe once, still not working.
//    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
//    private val loginResult: LiveData<Result<LoginResponse>> = _loginResult

//    fun userLogin(email: String, password: String) {
//        _loginResult.value = nusaRepository.login(email, password).value
//    }
//
//    fun observeLoginResult(): LiveData<Result<LoginResponse>> {
//        return loginResult
//    }

    fun userLogin(email: String, password: String) = nusaRepository.login(email, password)
}