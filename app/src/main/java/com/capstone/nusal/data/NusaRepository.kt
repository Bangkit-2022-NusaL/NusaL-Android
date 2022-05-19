package com.capstone.nusal.data

import com.capstone.nusal.data.remote.ApiService

class NusaRepository(
    private val apiService: ApiService
) {

    // Login
    // Register


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