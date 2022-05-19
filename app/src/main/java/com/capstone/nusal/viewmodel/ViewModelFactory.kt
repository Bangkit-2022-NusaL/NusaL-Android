package com.capstone.nusal.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory: ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory()
            }
    }
}