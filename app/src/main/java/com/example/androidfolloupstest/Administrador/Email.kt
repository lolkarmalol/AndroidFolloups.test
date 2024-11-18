package com.example.androidfolloupstest.Administrador
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidfolloupstest.R

class Email :ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection()
            NotificationBar()
            NotificationScreen()
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
                androidx.compose.material.Text(
                    "Etapa\nProductiva",
                    fontSize = 13.sp,
                    color = Color(0xFF009E00),
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .offset(x = (-5).dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                androidx.compose.material.Text(
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

            androidx.compose.material.DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                // Añadir nombre y rol en la parte superior del menú
                Column(
                    modifier = Modifier.padding(16.dp) // Espaciado
                ) {
                    androidx.compose.material.Text(
                        text = userName,
                        style = androidx.compose.material.MaterialTheme.typography.h6
                    )
                    androidx.compose.material.Text(
                        text = userRole,
                        style = androidx.compose.material.MaterialTheme.typography.body2
                    )
                }

                // Elementos del menú
                androidx.compose.material.DropdownMenuItem(onClick = {
                    expanded = false
                    // Navegar a la actividad de perfil
                    context.startActivity(Intent(context, PerfileActivity::class.java))
                }) {
                    androidx.compose.material.Text("Ver perfil")
                }
                androidx.compose.material.DropdownMenuItem(onClick = {
                    expanded = false
                    context.startActivity(Intent(context, InstructorActivity::class.java))
                }) {
                    androidx.compose.material.Text("Instructores")
                }
                androidx.compose.material.DropdownMenuItem(onClick = {
                    expanded = false
                    context.startActivity(Intent(context, ApprenticeActivity::class.java))
                }) {
                    androidx.compose.material.Text("Aprendices")
                }
                androidx.compose.material.DropdownMenuItem(onClick = {
                    expanded = false
                    context.startActivity(Intent(context, GraphicActivity::class.java))
                }) {
                    androidx.compose.material.Text("Gráficas")
                }
                androidx.compose.material.DropdownMenuItem(onClick = {
                    expanded = false
                    context.startActivity(Intent(context, TemplateActivity::class.java))
                }) {
                    androidx.compose.material.Text("Plantillas")
                }

                androidx.compose.material.DropdownMenuItem(onClick = {
                    expanded = false
                    context.startActivity(Intent(context, ConfiguracionActivity::class.java))
                    // Acción para configuración
                }) {
                    androidx.compose.material.Text("Configuración")
                }
                androidx.compose.material.DropdownMenuItem(onClick = {
                    expanded = false
                    // Acción para cerrar sesión
                }) {
                    androidx.compose.material.Text("Cerrar sesión")
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NotificationScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Contenedor principal
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                // Información de email
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.email), // Usa tu imagen
                        contentDescription = "Email Icon",
                        modifier = Modifier.size(40.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Text(text = "Asunto: xxxxxx", fontSize = 16.sp)
                        Text(text = "Para: xxxxxx", fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = "Fecha: xxxxxxxx", fontSize = 12.sp)
                }

                // Descripción
                Text(text = "Descripción", fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))

                // Área de texto sin fondo gris
                TextField(
                    value = "",
                    onValueChange = { /* Actualizar valor */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(4.dp),

                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFE0E0E0), // Sin fondo en el área editable
                        unfocusedIndicatorColor = Color.Transparent, // Línea inferior oculta cuando no está enfocado
                        focusedIndicatorColor = Color.Transparent // Línea inferior oculta cuando está enfocado
                    ),
                    shape = RoundedCornerShape(2.dp)
                )
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}
