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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.etapaproductiva.ui.theme.EtapaProductivaTheme

class PerfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EtapaProductivaTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "perfil") {
                    composable("perfil") { PerfilScreen(navController) }
                }
            }
        }
    }

    @Composable
    fun PerfilScreen(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HeaderSection()
            Spacer(modifier = Modifier.height(8.dp))
            NotificationBar()
            Spacer(modifier = Modifier.height(16.dp))
            SearchBar()
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                PerfilInfo()
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


            Spacer(modifier = Modifier.width(10.dp))

            Spacer(modifier = Modifier.width(2.dp))
        }
    }

    @Composable
    fun PerfilInfo() {
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
            // User Icon
            Image(
                painter = painterResource(id = R.drawable.user_icon), // Replace with your image resource
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "USUARIO",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Basic Information Section
            Text(text = "Datos básicos", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            InfoItem(label = "Nombres:", value = "Nombre del Usuario")
            InfoItem(label = "Apellidos:", value = "Apellido del Usuario")
            InfoItem(label = "Correo electrónico:", value = "usuario@ejemplo.com")
            InfoItem(label = "Cuenta Soy SENA:", value = "Activa")
            InfoItem(label = "Departamento:", value = "Departamento de Ejemplo")
            InfoItem(label = "Municipio:", value = "Municipio de Ejemplo")

            Text(text = "Modalidad que maneja", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            InfoItem(label = "Modalidad:", value = "Ejemplo")
            InfoItem(label = "Modalidad:", value = "Ejemplo")
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
                    .background(Color.White, shape = RoundedCornerShape(8.dp))  // Fondo blanco redondeado
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))  // Borde gris redondeado
                    .padding(8.dp)
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        EtapaProductivaTheme {
            PerfilScreen(rememberNavController())
        }
    }
}
