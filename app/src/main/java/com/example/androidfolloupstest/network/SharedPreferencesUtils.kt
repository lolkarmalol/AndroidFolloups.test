package com.example.androidfolloupstest.network

import android.content.Context

fun saveUserRole(context: Context, role: String) {
    val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putString("user_role", role)
        apply()
    }
}

fun getUserRole(context: Context): String? {
    val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    return sharedPref.getString("user_role", null)
}
