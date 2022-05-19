package com.capstone.nusal.di

import android.content.Context
import com.capstone.nusal.data.NusaRepository
import com.capstone.nusal.data.remote.ApiConfig

object Injection {

    fun provideRepository(context: Context): NusaRepository {
        val apiService = ApiConfig.getApiService()
        return NusaRepository.getInstance(apiService)
    }
}