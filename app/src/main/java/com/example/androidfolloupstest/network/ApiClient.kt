package com.example.androidfolloupstest.network

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response  // Asegúrate de importar la clase correcta

object ApiClient {
    private const val BASE_URL = "https://apietapaproductivatest-production-af30.up.railway.app/"

    val authService: AuthService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

        retrofit.create(AuthService::class.java)
    }
}

interface AuthService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>  // Aquí se especifica el tipo de respuesta esperado
}

data class LoginRequest(
    val email: String,
    val password: String
)

data class AuthResponse(
    val token: String,
    val role: String // El rol del usuario
)
