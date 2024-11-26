package com.example.androidfolloupstest.network

import android.content.Context
import android.content.SharedPreferences

fun saveAuthToken(context: Context, token: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("auth_token", token)
        apply()
    }
}

fun saveUserRole(context: Context, role: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("user_role", role)
        apply()
    }
}

fun getUserRole(context: Context): String? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("user_role", null)
}
