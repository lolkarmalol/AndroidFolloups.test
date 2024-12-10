package com.example.androidfollowuptest.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.androidfollowuptest.LoginScreen
import com.example.androidfollowuptest.model.User
import com.example.androidfollowuptest.ui.screens.*

// Definición del grafo de navegación
fun NavGraphBuilder.appNavGraph(navController: NavController) {
    composable("login") {
        LoginScreen(navController = navController)
    }

    // Pantalla para el instructor
    composable(
        "Instructor_home/{firstName}/{lastName}/{phone}/{address}",
        arguments = listOf(
            navArgument("firstName") { type = NavType.StringType },
            navArgument("lastName") { type = NavType.StringType },
            navArgument("phone") { type = NavType.StringType },
            navArgument("address") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        InstructorHomeScreen(
            firstName = backStackEntry.arguments?.getString("firstName") ?: "",
            lastName = backStackEntry.arguments?.getString("lastName") ?: "",
            phone = backStackEntry.arguments?.getString("phone") ?: "",
            address = backStackEntry.arguments?.getString("address") ?: ""
        )
    }
//
    // Pantalla para el aprendiz
    composable(
        "Apprentice_home/{firstName}/{lastName}/{phone}/{address}",
        arguments = listOf(
            navArgument("firstName") { type = NavType.StringType },
            navArgument("lastName") { type = NavType.StringType },
            navArgument("phone") { type = NavType.StringType },
            navArgument("address") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        ApprenticeHomeScreen(
            firstName = backStackEntry.arguments?.getString("firstName") ?: "",
            lastName = backStackEntry.arguments?.getString("lastName") ?: "",
            phone = backStackEntry.arguments?.getString("phone") ?: "",
            address = backStackEntry.arguments?.getString("address") ?: ""
        )
    }
}

// Función para redirigir al home según el rol del usuario
fun redirectToHome(user: User, navController: NavController) {
    val route = when (user.role) {
        "trainer" -> "Instructor_home/${user.firstName}/${user.lastName}/${user.phone}/${user.address}"
        "apprentice" -> "Apprentice_home/${user.firstName}/${user.lastName}/${user.phone}/${user.address}"
        else -> null
    }
    route?.let {
        navController.navigate(it) {
            popUpTo("login") { inclusive = true }
        }
    }
}
