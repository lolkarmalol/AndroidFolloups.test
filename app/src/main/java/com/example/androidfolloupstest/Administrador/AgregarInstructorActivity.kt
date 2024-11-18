package com.example.androidfolloupstest.Administrador

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidfolloupstest.R

class AgregarInstructorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }

    @Composable
    fun AgregarInstructorScreen(navController: NavController) {
        val scrollState = rememberScrollState() // Crear estado de desplazamiento

        Column(
            modifier = Modifier
                .fillMaxSize()

                .verticalScroll(scrollState) // Habilitar desplazamiento
        ) {
            HeaderSection()
            Spacer(modifier = Modifier.height(8.dp))

            NotificationBar() // Mostrar la barra de notificación

            Spacer(modifier = Modifier.height(16.dp))
            InstructorForm()
            Spacer(modifier = Modifier.height(16.dp))
            ActionButtons(navController)
        }
    }

    @Composable
    fun HeaderSection() {
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
                    color = Color(0xFF009E00), // Verde
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .offset(x = (-5).dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    "Centro de Comercio y Servicios",
                    fontSize = 14.sp,
                    color = Color(0xFF009E00), // Verde
                    modifier = Modifier.offset(x = (-30).dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            UserIcon()
        }
    }
    @Composable
    fun UserIcon() {
        var expanded by remember { mutableStateOf(false) }

        // Obtén el contexto actual
        val context = LocalContext.current

        // Reemplaza estos valores con los datos reales
        val userName = "Paula Valencia" // Nombre del usuario
        val userRole = "Administrador" // Rol del usuario

        Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
            Image(
                painter = painterResource(id = R.drawable.user_icon),
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(45.dp)
                    .clickable { expanded = true }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                // Añadir nombre y rol en la parte superior del menú
                Column(
                    modifier = Modifier.padding(16.dp) // Espaciado
                ) {
                    Text(text = userName, style = MaterialTheme.typography.h6)
                    Text(text = userRole, style = MaterialTheme.typography.body2)
                }

                // Elementos del menú
                DropdownMenuItem(onClick = {
                    expanded = false
                    // Navegar a la actividad de perfil
                    context.startActivity(Intent(context, PerfileActivity::class.java))
                }) {
                    Text("Ver perfil")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    context.startActivity(Intent(context, InstructorActivity::class.java))
                }) {
                    Text("Instructores")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    context.startActivity(Intent(context, ApprenticeActivity::class.java))
                }) {
                    Text("Aprendices")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    context.startActivity(Intent(context, GraphicActivity::class.java))
                }) {
                    Text("Gráficas")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    context.startActivity(Intent(context, TemplateActivity::class.java))
                }) {
                    Text("Plantillas")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    context.startActivity(Intent(context, ConfiguracionActivity::class.java))
                    // Acción para configuración
                }) {
                    Text("Configuración")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    // Acción para cerrar sesión
                }) {
                    Text("Cerrar sesión")
                }
            }
        }
    }

    @Composable
    fun NotificationBar() {
        // Obtén el contexto actual
        val context = LocalContext.current

        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(64.dp)
                .background(Color(0xFF009E00)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.notificaciones_icon),
                contentDescription = "Notification Icon",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        // Navega a la actividad AgregarInstructorActivity
                        val intent = Intent(context, NotificacionesActivity::class.java)
                        context.startActivity(intent) // Inicia la nueva actividad
                    },
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }

    @Composable
    fun InstructorForm() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, shape = MaterialTheme.shapes.medium) // Fondo gris
                .border(
                    3.dp,
                    Brush.verticalGradient(listOf(Color.Gray.copy(alpha = 0.5f), Color.Transparent)),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(16.dp) // Padding interno
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                val labels = listOf(
                    "Nombre", "Apellido", "Cédula", "Correo", "Celular",
                    "Programa", "Total de horas", "Horas realizadas",
                    "Fecha de inicio", "Fecha de fin",
                    "País", "Departamento", "Municipio",
                    "Barrio", "Dirección"
                )

                labels.forEach { label ->
                    Row(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically // Centra verticalmente los elementos
                    ) {
                        Text(
                            text = "$label:",
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentWidth(Alignment.Start) // Alinear a la izquierda
                        )
                        TextField(
                            value = "", // Aquí puedes manejar el estado
                            onValueChange = {},
                            modifier = Modifier
                                .weight(2f)
                                .height(50.dp)
                                .padding(horizontal = 2.dp),
                            placeholder = { Text(text = "Ingrese $label") },
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center) // Centra el texto en el TextField
                        )
                    }
                }
            }
        }
    }


    @Composable
    fun ActionButtons(navController: NavController) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    // Lógica de confirmar
                    navController.navigate("instructor") // Ejemplo de navegación
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF009E00)) // Color verde
            ) {
                Text("Confirmar", color = Color.White) // Texto en blanco
            }

            Button(
                onClick = {
                    // Lógica de cancelar
                    finish() // Cierra la actividad actual
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray) // Color rojo para cancelar
            ) {
                Text("Cancelar", color = Color.White)  // Texto en blanco
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
            AgregarInstructorScreen(rememberNavController())

    }
}

