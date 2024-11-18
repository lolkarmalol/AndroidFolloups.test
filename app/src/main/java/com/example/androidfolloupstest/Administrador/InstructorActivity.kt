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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidfolloupstest.R

class InstructorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "instructor") {
                    composable("instructor") { InstructorScreen(navController) }

            }
        }
    }

    @Composable
    fun InstructorScreen(navController: NavController) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item { HeaderSection() }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { NotificationBar() }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { SearchBar() }
            items(10) { InstructorRow(navController) } // Muestra filas de instructores
        }
    }

    @Composable
    fun InstructorRow(navController: NavController) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Crear dos cards para los instructores
            repeat(2) {
                InstructorCard(modifier = Modifier.size(170.dp), navController)
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

            UserIcon() // Aquí se llama al nuevo Composable UserIcon
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
    fun SearchBar() {
        val context = LocalContext.current // Obtener el contexto actual

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Spacer(modifier = Modifier.width(10.dp))

            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .padding(horizontal = 2.dp),
                placeholder = { Text(text = "Buscar...") }
            )

            Spacer(modifier = Modifier.width(2.dp))

            IconButton(onClick = {
                // Navega a la actividad AgregarInstructorActivity
                val intent = Intent(context, AgregarInstructorActivity::class.java)
                context.startActivity(intent) // Inicia la nueva actividad
            }, modifier = Modifier.size(36.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.mas),
                    contentDescription = "Agregar",
                    tint = (Color(0xFF009E00)),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }

    @Composable
    fun InstructorCard(modifier: Modifier = Modifier, navController: NavController) {
        val context = LocalContext.current // Obtener el contexto actual

        Column(
            modifier = modifier
                .padding(8.dp)
                .border(2.dp, (Color(0xFF009E00)), shape = MaterialTheme.shapes.medium)
                .padding(16.dp)
                .clickable {
                    // Navegar a la actividad InstructorPerfilActivity al hacer clic
                    context.startActivity(Intent(context, InstructorPerfilActivity::class.java))
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.instructor),
                contentDescription = "Instructor Icon",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Nombre Completo", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text(text = "Cédula", fontSize = 10.sp)
            Text(text = "Sede", fontSize = 10.sp)
            Text(text = "Aprendices: 0", fontSize = 10.sp)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
            InstructorScreen(rememberNavController())

    }
}
