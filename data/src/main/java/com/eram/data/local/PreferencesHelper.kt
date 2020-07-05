package com.eram.data.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesHelper @Inject constructor(private val context: Context) {
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
            "pref",
            Context.MODE_PRIVATE
        )
    }

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, default: String?): String? {
        return sharedPreferences.getString(key, default)
    }
}