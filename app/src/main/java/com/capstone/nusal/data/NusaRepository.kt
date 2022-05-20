package com.capstone.nusal.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.capstone.nusal.data.remote.ApiService
import com.capstone.nusal.data.remote.LoginResponse
import com.capstone.nusal.data.remote.RegisterResponse

class NusaRepository(
    private val apiService: ApiService
) {

    private val _isLoginSuccess = MutableLiveData<LoginResponse>()
    private val isLoginSuccess: LiveData<LoginResponse> = _isLoginSuccess

    private val _isRegisterSuccess = MutableLiveData<RegisterResponse>()
    private val isRegisterSuccess: LiveData<RegisterResponse> = _isRegisterSuccess

    fun login(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)

        try {
            val response = apiService.userLogin(email, password)

            _isLoginSuccess.value = response

            val result: LiveData<Result<LoginResponse>> = isLoginSuccess.map {
                Result.Success(it)
            }

            emitSource(result)
        } catch (e: Exception) {
            Log.d("NusaRepository", "login: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun register(name: String, email: String, password: String): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)

        try {
            val response = apiService.userRegister(name, email, password)

            _isRegisterSuccess.value = response

            val result: LiveData<Result<RegisterResponse>> = isRegisterSuccess.map {
                Result.Success(it)
            }

            emitSource(result)
        } catch (e: Exception) {
            Log.d("NusaRepository", "login: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }


    companion object {
        @Volatile
        private var instance: NusaRepository? = null

        fun getInstance(
            apiService: ApiService
        ): NusaRepository =
            instance ?: synchronized(this) {
                instance ?: NusaRepository(apiService)
            }.also { instance = it }
    }
}