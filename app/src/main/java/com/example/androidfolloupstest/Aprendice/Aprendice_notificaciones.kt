package com.example.androidfolloupstest.Aprendice

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.androidfolloupstest.R

@Composable
fun NotificacionScreen (navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
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
                modifier = Modifier
                    .fillMaxWidth()  // Ocupa todo el ancho de la pantalla

                    .wrapContentSize(Alignment.TopEnd),
                contentAlignment = Alignment.CenterStart

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
                                text = "Dayana Cantero",
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
                        navController.navigate("configuracion")
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
        // NOTIFICACIONES
        val notificaciones = listOf(
            Triple("Notificación 1", "Asunto 1", "Fecha 1"),
            Triple("Notificación 2", "Asunto 2", "Fecha 2"),
            // Agrega más notificaciones aquí
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar()
            EmailList(notificaciones, navController)
        }
    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SearchBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Acción del desplegable */ }) {
                Image(
                    painter = painterResource(id = R.drawable.menu1),
                    contentDescription = "User Icon",
                    modifier = Modifier.size(45.dp)
                )
            }

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

            IconButton(
                onClick = { /* Acción al redactar */ },
                modifier = Modifier.size(25.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mas),
                    contentDescription = "User Icon",
                    modifier = Modifier.size(45.dp)
                )
            }
        }
    }

    @Composable
    fun EmailList(notificaciones: List<Triple<String, String, String>>, navController: NavController) {
        LazyColumn {
            itemsIndexed(notificaciones) { index, notificacion ->
                EmailItem(
                    title = notificacion.first,
                    subject = notificacion.second,
                    date = notificacion.third,
                    onClick = {
                        navController.navigate("detallenotificacion/$index")
                    }
                )
            }
        }
    }

    @Composable
    fun EmailItem(title: String, subject: String, date: String, onClick: () -> Unit) {
        var snackbarVisible by remember { mutableStateOf(false) }
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
                .clickable { onClick() },  // Navegar al detalle
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(subject, color = Color.Gray, fontSize = 12.sp)
                Text(date, color = Color.Gray, fontSize = 11.sp)
            }

            IconButton(onClick = { snackbarVisible = true }) { // Al hacer clic, muestra el Snackbar
                Image(
                    painter = painterResource(id = R.drawable.papelera),
                    contentDescription = "Eliminar",
                    modifier = Modifier.size(24.dp)

                )
            }
        }

        // Mostrar el Snackbar cuando snackbarVisible es true
        if (snackbarVisible) {
            Snackbar(
                backgroundColor = Color.White,
                action = {
                    Button(
                        onClick = { snackbarVisible = false }, // Oculta el Snackbar al presionar "Cerrar"
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                    ) {
                        Text("Cerrar", color = Color.Black)
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Mensaje eliminado", color = Color.Black)
            }
        }
    }