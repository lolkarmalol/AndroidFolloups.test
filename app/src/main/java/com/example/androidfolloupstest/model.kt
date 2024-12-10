package com.example.androidfollowuptest.model

data class User(
    val email: String,
    val password: String,
    val role: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val address: String
)
// Lista de usuarios locales
val users = listOf(
    User(
        email = "instructor@example.com",
        password = "password1",
        role = "trainer",
        firstName = "Carlos",
        lastName = "Pérez",
        phone = "3001234567",
        address = "Calle 123, Bogotá"
    ),
    User(
        email = "aprendiz@example.com",
        password = "password2",
        role = "apprentice",
        firstName = "Ana",
        lastName = "Gómez",
        phone = "3107654321",
        address = "Carrera 45, Medellín"
    )
)

