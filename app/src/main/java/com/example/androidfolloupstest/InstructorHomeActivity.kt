package com.example.androidfollowuptest.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material3.*


@Composable
fun InstructorHomeScreen(firstName: String, lastName: String, phone: String, address: String) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Instructor Home")
        Text("Name: $firstName $lastName")
        Text("Phone: $phone")
        Text("Address: $address")
    }
}
