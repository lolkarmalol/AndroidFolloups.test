package com.example.androidfolloupstest.network


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("login")  // Asegúrate de cambiar la ruta a la de tu API
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}
