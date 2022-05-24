package com.capstone.nusal.viewmodel.datastore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.nusal.data.SessionDataStore
import kotlinx.coroutines.launch

class SessionViewModel(private val session: SessionDataStore): ViewModel() {

    fun saveSession(token: String) {
        viewModelScope.launch {
            session.saveSession(token)
        }
    }

    fun clearSession() {
        viewModelScope.launch {
            session.clearSession()
        }
    }

    fun isLogin(): LiveData<Boolean> = session.isLoggedIn().asLiveData()

    fun getToken(): LiveData<String> = session.getToken().asLiveData()

}