package com.example.androidfolloupstest.Administrador

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
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

class AprendizPerfilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }

    @Composable
    fun AprendizScreen(navController: NavController) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HeaderSection()
            Spacer(modifier = Modifier.height(8.dp))
            NotificationBar()
            Spacer(modifier = Modifier.height(16.dp))
            SearchBar()
            Spacer(modifier = Modifier.height(16.dp))
            // Envolvemos en una columna con scroll
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                AprendizInfo()
            }
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color(0xFF009E00)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.notificaciones_icon),
                contentDescription = "Notification Icon",
                modifier = Modifier.size(60.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }

    @Composable
    fun SearchBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Se ha eliminado el IconButton que contiene la flecha
            Spacer(modifier = Modifier.width(10.dp))
            Spacer(modifier = Modifier.width(2.dp))
        }
    }

    @Composable
    fun AprendizInfo() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFFE0E0E0), shape = MaterialTheme.shapes.medium)
                .border(
                    3.dp,
                    Brush.verticalGradient(listOf(Color.Gray.copy(alpha = 20f), Color.Transparent)),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.aprendiz), // Reemplaza con tu recurso de imagen
                contentDescription = "Aprendiz Icon",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "APRENDIZ",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "informacion Modalidad ", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            InfoItem(label = "Tipo de Modalidad :", value = "Juan Perez")
            InfoItem(label = "Fecha Inicio :", value = "Gomez Lopez")
            InfoItem(label = "Fecha Final:", value = "juan.perez@example.com")
            InfoItem(label = "Proceso:", value = "juan.perez@sena.edu.co")

            TimelineContainer() // Línea temporal se coloca aquí

            // Basic Information Section
            Text(text = "Datos básicos", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            InfoItem(label = "Nombres:", value = "Juan Perez")
            InfoItem(label = "Apellidos:", value = "Gomez Lopez")
            InfoItem(label = "Correo electrónico:", value = "juan.perez@example.com")
            InfoItem(label = "Cuenta SENA:", value = "juan.perez@sena.edu.co")
            InfoItem(label = "Departamento:", value = "Antioquia")
            InfoItem(label = "Municipio:", value = "Medellín")
            InfoItem(label = "Nivel Académico:", value = "Técnico")

            // Espaciado antes de los botones
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { /* Acción de Actualizar */ },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF009E00), // Fondo verde
                        contentColor = Color.White          // Texto blanco
                    )
                ) {
                    Text(text = "Actualizar")
                }

                Button(
                    onClick = { /* Acción de Cancelar */ },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Gray, // Fondo gris
                        contentColor = Color.White  // Texto blanco
                    )
                ) {
                    Text(text = "Cancelar")
                }
            }
        }
    }

    @Composable
    fun TimelineContainer() {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .padding(vertical = 8.dp)
                .background(Color(0xFFF7F7F7)),
            shape = RoundedCornerShape(12.dp),
            elevation = 4.dp // Cambiado a 'elevation'
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Línea Temporal (Etapa de seguimiento)",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp),
                    textAlign = TextAlign.Center
                )

                // Contenedor del gráfico de línea temporal
                Box(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopStart
                ) {
                    TimelineChart(events = listOf(
                        "Asignación - 2023-12-29",
                        "Inicio Etapa Productiva - 2024-01-01",
                        "Primera Visita - 2024-02-01",
                        "Segunda Visita - 2024-04-01",
                        "Tercera Visita - 2024-06-01",
                        "Finalización - 2024-08-01"
                    ))
                }
            }
        }
    }

    // Composable que dibuja la línea temporal
    @Composable
    fun TimelineChart(events: List<String>) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            events.forEachIndexed { index, event ->
                TimelineEvent(eventText = event, isCompleted = index < 2) // Marcamos los primeros dos como completados
            }
        }
    }

    // Componente para representar cada evento de la línea temporal
    @Composable
    fun TimelineEvent(eventText: String, isCompleted: Boolean) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            // Dibuja el círculo del evento
            Canvas(modifier = Modifier.size(16.dp)) {
                drawCircle(
                    color = if (isCompleted) Color.Green else Color.Gray,
                    radius = size.minDimension / 2
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Texto del evento
            Text(text = eventText, fontSize = 14.sp)
        }
    }

    @Composable
    fun InfoItem(label: String, value: String) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            Text(text = label, fontWeight = FontWeight.Bold)
            Text(
                text = value,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
            AprendizScreen(rememberNavController())

    }
}
