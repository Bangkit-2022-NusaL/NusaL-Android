package com.capstone.nusal.data.pref

import android.content.Context

internal class OnboardingPref(context: Context) {

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setPref(pref: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(IS_FIRST_TIME, pref)
        editor.apply()
    }

    fun isFirstTime(): Boolean {
        return preferences.getBoolean(IS_FIRST_TIME, true)
    }

    companion object {
        private const val PREFS_NAME = "onboarding_pref"
        private const val IS_FIRST_TIME = "is_first_time"
    }
}