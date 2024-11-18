package com.example.androidfolloupstest.Administrador

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.text.font.FontWeight
import com.example.androidfolloupstest.R

class NotificacionesActivity : ComponentActivity() {
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
            SearchBar()
            EmailList()
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
            // Espacio para futuros elementos de la barra de notificaciones
        }
    }

    @Composable
    fun SearchBar() {
        var expanded by remember { mutableStateOf(false) }

        // Obtén el contexto aquí, en el inicio de la función composable
        val context = LocalContext.current

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícono desplegable a la izquierda del campo de búsqueda
            Box {
                IconButton(onClick = { expanded = true }) {
                    Image(
                        painter = painterResource(id = R.drawable.menu1),
                        contentDescription = "Menu Icon",
                        modifier = Modifier.size(20.dp)
                    )
                }

                // DropdownMenu que se despliega al hacer clic en el ícono
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(onClick = {
                        expanded = false
                        // Acción al seleccionar "Recibidos"
                    }) {
                        Text("Recibidos")
                    }
                    DropdownMenuItem(onClick = {
                        expanded = false
                        // Acción al seleccionar "Enviados"
                    }) {
                        Text("Enviados")
                    }
                    DropdownMenuItem(onClick = {
                        expanded = false
                        // Acción al seleccionar "Papelera"
                    }) {
                        Text("Papelera")
                    }
                }
            }

            // Campo de búsqueda
            TextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Buscar...") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 6.dp, end = 6.dp)
                    .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(2.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            // Botón de redactar
            IconButton(
                onClick = {
                    val intent = Intent(context, RedactarActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.size(25.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mas),
                    contentDescription = "Redactar Icon",
                    modifier = Modifier.size(45.dp)
                )
            }
        }
    }

    @Composable
    fun EmailList() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            LazyColumn {
                items(7) {
                    EmailItem(
                        title = "Título de la Notificación",
                        subject = "Asunto de la Notificación",
                        date = "Fecha"
                    )
                }
            }
        }
    }

    @Composable
    fun EmailItem(title: String, subject: String, date: String) {
        var snackbarVisible by remember { mutableStateOf(false) }

        // Obtén el contexto actual
        val context = LocalContext.current

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(2.dp)
                )
                .padding(8.dp)
                .clickable { /* Acción al ver el email */ },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = {
                        val intent = Intent(context, Email::class.java)
                        // Puedes pasar datos adicionales a la actividad si lo necesitas
                        intent.putExtra("EXTRA_SUBJECT", subject)
                        intent.putExtra("EXTRA_TITLE", title)
                        intent.putExtra("EXTRA_DATE", date)
                        context.startActivity(intent)
                    })
            ) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(subject, color = Color.Gray, fontSize = 12.sp)
                Text(date, color = Color.Gray, fontSize = 11.sp)
            }

            // Coloca aquí la imagen como un botón
            IconButton(onClick = {
                snackbarVisible = true // Muestra el snackbar al hacer clic
            }) {
                Image(
                    painter = painterResource(id = R.drawable.papelera),
                    contentDescription = "Papelería Icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Mostrar Snackbar
        if (snackbarVisible) {
            Snackbar(
                backgroundColor = Color.White, // Fondo blanco
                action = {
                    Button(
                        onClick = { snackbarVisible = false },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray) // Botón gris
                    ) {
                        Text("Cerrar", color = Color.Black) // Texto negro
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Mensaje eliminado", color = Color.Black) // Texto negro para el mensaje
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}
