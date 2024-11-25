package com.example.androidfolloupstest.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

object ApiClient {
    private const val BASE_URL = "https://apietapaproductivatest-production-af30.up.railway.app/"  // Cambia esto a tu URL real

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authService: AuthService = retrofit.create(AuthService::class.java)
}

