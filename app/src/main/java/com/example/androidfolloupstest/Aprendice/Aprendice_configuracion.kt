package com.example.androidfolloupstest.Aprendice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.androidfolloupstest.R

@Composable
fun Aprendice_configuracion(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    // Variables para almacenar el texto de los campos
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = 30.dp)
    ) {
        // Primer LinearLayout en horizontal
        Row(
            modifier = Modifier.padding(0.dp),
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

            Column {
                Text(
                    "Etapa\nProductiva",
                    fontSize = 13.sp,
                    color = Color(0xFF009E00),
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .offset(x = (-5).dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    "Centro de Comercio y Servicios",
                    fontSize = 14.sp,
                    color = Color(0xFF009E00),
                    modifier = Modifier.offset(x = (-30).dp)
                )
            }

            // Texto "Dayana" con el menú desplegable
            Box(
                modifier = Modifier.wrapContentSize(Alignment.TopEnd)
                    .align(Alignment.CenterVertically)

            ) {

                Text(
                    text = "Dayana",
                    modifier = Modifier
                        .background(Color(0xFFFFFFFF))
                        .shadow(4.dp, RoundedCornerShape(20.dp))
                        .padding(10.dp)
                        .clickable { expanded = true },


                    // Hacemos que el texto sea clickable para mostrar el menú
                )

// Menú desplegable
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },


                    ) {
                    // Información del usuario
                    Row(modifier = Modifier.padding(bottom = 12.dp)) {
                        Column {
                            Text(
                                text = "Nombre Usuario",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Aprendiz",
                                fontSize = 14.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                    DropdownMenuItem(onClick = {
                        expanded = false
                        navController.navigate("perfil") // Navegar a la pantalla de perfil

                    }) {
                        Text("Ver perfil")
                    }

                    DropdownMenuItem(onClick = {
                        expanded = false
                        // Navegar a la pantalla de configuración u otro lugar
                    }) {
                        Text("Configuración")
                    }

                    DropdownMenuItem(onClick = {
                        expanded = false
                        // Implementar la acción de cerrar sesión
                    }) {
                        Text("Cerrar sesión")
                    }
                }

            }
        }
        // Segundo LinearLayout que aparecerá debajo del primero
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color(0xFF009E00)),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom, // Mueve el contenido al final
                horizontalAlignment = Alignment.End // Alinea el contenido a la derecha
            ) {
                // Contenedor para el ImageButton y TextView

                Image(
                    painter = painterResource(id = R.drawable.notificaciones_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)

                        .clickable {
                            navController.navigate("notificaciones") // Navega a la pantalla de notificaciones
                        },
                    colorFilter = ColorFilter.tint(Color.White)


                )
            }
            // Botones centrados
            // Textos centrados
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center, // Centra verticalmente
                horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center // Centra horizontalmente dentro de la fila
                ) {
                    Text(
                        text = "Inicio",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .clickable { navController.navigate("home") }
                            .padding(8.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Calendario",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .clickable { navController.navigate("calendario") }
                            .padding(8.dp)
                    )

                }
            }
        }

//CONFIGURACION

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White)
        ) {
            // Flecha para volver a la pantalla anterior


            // Título "Configuración"
            Text(
                text = "Configuración",
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )

            // Tarjeta de "Cambio de Contraseña"
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Cambio de Contraseña",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Campo de Contraseña Actual
                    OutlinedTextField(
                        value = currentPassword,
                        onValueChange = { currentPassword = it },
                        label = { Text("Contraseña Actual") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Campo de Nueva Contraseña
                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        label = { Text("Nueva Contraseña") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Campo de Confirmación de Contraseña
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirmar Nueva Contraseña") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón para actualizar la contraseña
                    Button(
                        onClick = {
                            // Verificar si las contraseñas coinciden
                            if (newPassword == confirmPassword) {
                                resultMessage = "Actualización exitosa" // Si coinciden
                            } else {
                                resultMessage = "Las contraseñas no coinciden" // Si no coinciden
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF009E00))
                    ) {
                        Text("Actualizar Contraseña", color = Color.White)
                    }

                    // Mostrar el mensaje de resultado
                    if (resultMessage.isNotEmpty()) {
                        Text(
                            text = resultMessage,
                            color = if (resultMessage == "Actualización exitosa") Color.Green else Color.Red,
                            modifier = Modifier.padding(top = 16.dp))
                    }
                }
            }
        }
    }
}
