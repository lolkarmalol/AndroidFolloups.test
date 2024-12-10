package com.example.androidfollowuptest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.androidfolloupstest.HomeApprenticeScreen
import com.example.androidfolloupstest.R
import com.example.androidfolloupstest.ui.theme.GreenTheme
import com.example.androidfollowuptest.model.User
import com.example.androidfollowuptest.model.users
import com.example.androidfollowuptest.ui.screens.ApprenticeHomeScreen
import com.example.androidfollowuptest.ui.screens.InstructorHomeScreen

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreenTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(navController = navController)
                    }

                    composable(
                        "Instructor_home/{firstName}/{lastName}/{phone}/{address}",
                        arguments = listOf(
                            navArgument("firstName") { type = NavType.StringType },
                            navArgument("lastName") { type = NavType.StringType },
                            navArgument("phone") { type = NavType.StringType },
                            navArgument("address") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val firstName = backStackEntry.arguments?.getString("firstName") ?: ""
                        val lastName = backStackEntry.arguments?.getString("lastName") ?: ""
                        val phone = backStackEntry.arguments?.getString("phone") ?: ""
                        val address = backStackEntry.arguments?.getString("address") ?: ""
                        InstructorHomeScreen(firstName, lastName, phone, address)
                    }

                    composable(
                        "Apprentice_home/{firstName}/{lastName}/{phone}/{address}",
                        arguments = listOf(
                            navArgument("firstName") { type = NavType.StringType },
                            navArgument("lastName") { type = NavType.StringType },
                            navArgument("phone") { type = NavType.StringType },
                            navArgument("address") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val firstName = backStackEntry.arguments?.getString("firstName") ?: ""
                        val lastName = backStackEntry.arguments?.getString("lastName") ?: ""
                        val phone = backStackEntry.arguments?.getString("phone") ?: ""
                        val address = backStackEntry.arguments?.getString("address") ?: ""

                        // Pasar los parámetros correctamente a la pantalla HomeApprenticeScreen
                        HomeApprenticeScreen(navController, firstName, lastName, phone, address)
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    // Usamos un Column para la estructura general
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp) // Aseguramos que no haya padding extra en la columna principal
    ) {
        // Barra de encabezado verde
        LoginHeader()

        // Colocamos la imagen y el formulario dentro de un Column
        // Para que la tabla de usuario quede en la parte inferior de la pantalla
        Spacer(modifier = Modifier.weight(1f)) // Esto empuja el formulario hacia abajo

        // Contenedor que contiene la imagen a la izquierda y el formulario abajo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp) // Padding alrededor del Row
        ) {
            // Imagen a la izquierda
            Image(
                painter = painterResource(id = R.drawable.img), // Aquí reemplaza con la imagen que necesites
                contentDescription = "Imagen encima de la tabla de usuario",
                modifier = Modifier
                    .width(200.dp) // Ajusta el tamaño de la imagen
                    .height(200.dp)
                    .padding(end = 20.dp) // Espacio entre la imagen y el formulario
            )
        }

        // Tabla de usuario centrada abajo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(Color.White.copy(alpha = 0.9f), shape = RoundedCornerShape(12.dp))
                .shadow(4.dp)
                .align(Alignment.CenterHorizontally) // Centra el formulario
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp) // Padding de 20dp alrededor del formulario
            ) {
                Text(
                    text = "USUARIO",
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF003366)),
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 20.dp)
                )

                // Campos de entrada
                InputField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Correo electrónico",
                    icon = painterResource(R.drawable.aprendiz)
                )
                InputField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Contraseña",
                    icon = painterResource(R.drawable.contra),
                    isPassword = true,
                    showPassword = showPassword,
                    onPasswordVisibilityChanged = { showPassword = it }
                )

                // Mostrar mensaje de error si es necesario
                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
                }

                // Botón de inicio de sesión
                Button(
                    onClick = {
                        isLoading = true
                        val user = users.find { it.email == email && it.password == password }
                        if (user != null) {
                            isLoading = false
                            redirectToHome(user, navController)
                        } else {
                            errorMessage = "Credenciales incorrectas"
                            isLoading = false
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009E00))
                ) {
                    Text(text = if (isLoading) "Cargando..." else "Iniciar Sesión", color = Color.White)
                }

                // Enlace para recuperar la contraseña
                Spacer(modifier = Modifier.height(20.dp))
                TextButton(onClick = { /* Acción de "Olvidé mi contraseña" */ }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(text = "¿Olvidaste tu contraseña?", color = Color(0xFF003366))
                }
            }
        }
    }
}
//


@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: Painter,
    isPassword: Boolean = false,
    showPassword: Boolean = false,
    onPasswordVisibilityChanged: (Boolean) -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = icon, contentDescription = label, modifier = Modifier.size(22.dp))
            Spacer(modifier = Modifier.width(10.dp))

            if (isPassword) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { /* Hide keyboard */ }),
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 16.sp, color = Color.Black)
                )
            } else {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { /* Hide keyboard */ }),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 16.sp, color = Color.Black)
                )
            }
        }
    }
}

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

@Composable
fun LoginHeader() {
    Column {
        // Barra de encabezado verde sin padding adicional
        Row(
            modifier = Modifier.padding(10.dp), // No hay padding en la barra
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_sena),
                contentDescription = "Logo SENA",
                modifier = Modifier.size(70.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_etapaproductiva),
                contentDescription = "Logo Etapa Productiva",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            // Contenedor de texto con padding
            Column(
                modifier = Modifier.padding(top = 20.dp) // Padding agregado al contenedor de texto
            ) {
                Text(
                    "Etapa\nProductiva",
                    fontSize = 13.sp,
                    color = Color(0xFF009E00),
                    modifier = Modifier.offset(x = (-5).dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    "Centro de Comercio y Servicios",
                    fontSize = 14.sp,
                    color = Color(0xFF009E00),
                    modifier = Modifier.offset(x = (-30).dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        // Línea verde al final, de extremo a extremo
        androidx.compose.material.Divider(
            modifier = Modifier
                .fillMaxWidth()  // Asegura que ocupe todo el ancho disponible
                .height(50.dp)  // Establece la altura a 50dp
                .background(Color(0xFF009E00))  // Color verde
                .padding(0.dp) // Elimina cualquier relleno que pudiera generar un espacio en los bordes
        )
    }
}
