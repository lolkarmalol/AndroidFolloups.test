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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.androidfolloupstest.R

@Composable
fun DetalleNotificacionScreen(navController: NavHostController, navBackStackEntry: NavBackStackEntry) {
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
            // Texto "Dayana" con el menú desplegable
            Box(
                modifier = Modifier
                    .fillMaxWidth()  // Ocupa todo el ancho de la pantalla

                    .wrapContentSize(Alignment.TopEnd),
                contentAlignment = Alignment.CenterStart

            ) {

                androidx.compose.material.Text(
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
                            androidx.compose.material.Text(
                                text = "Dayana Cantero",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            androidx.compose.material.Text(
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
                        androidx.compose.material.Text("Ver perfil")
                    }

                    DropdownMenuItem(onClick = {
                        expanded = false
                        // Navegar a la pantalla de configuración u otro lugar
                        navController.navigate("configuracion")
                    }) {
                        androidx.compose.material.Text("Configuración")
                    }

                    DropdownMenuItem(onClick = {
                        expanded = false
                        // Implementar la acción de cerrar sesión
                    }) {
                        androidx.compose.material.Text("Cerrar sesión")
                    }
                }

            }


        }

        // Segundo LinearLayout que aparecerá debajo del primero
        androidx.compose.material3.Surface(
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
                    androidx.compose.material.Text(
                        text = "Inicio",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .clickable { navController.navigate("home") }
                            .padding(8.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    androidx.compose.material.Text(
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Contenedor superior con información de la notificación
            TrainerContainer(navController)

            Spacer(
                modifier = Modifier.height(16.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(12.dp))
                    .background(Color(0xFFF7F7F7)),
            )
            // Contenedor inferior con detalles y botones
            VisitContainer()
        }
    }
}

@Composable
fun TrainerContainer(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp)
            .background(Color(0xFFD9D9D9))
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp)),

        verticalAlignment = Alignment.CenterVertically,

        horizontalArrangement = Arrangement.SpaceBetween,

    ) {

        Text(
            text = "Instructor",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal
            )
        )
        Text(
            text = "08:39am. 30 mar",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Composable
fun VisitContainer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFEFE))
            .border(1.dp, Color(0xFF2F3E4C), RoundedCornerShape(20.dp))
            .padding(16.dp)


    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(59.dp)
                .background(Color(0xFF009E00))
                .clip(RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "¡Mariani Dorado ha solicitado programar una visita!",
                color = Color.White,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Información de la visita
        Text("Fecha: 01/04/2024")
        Text("Hora: 08:30am")

        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        // Botones de aceptación y rechazo
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { /* Acción de rechazar */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD9D9D9)),
                shape = RoundedCornerShape(20.dp),

            ) {
                Text("Rechazar", color = Color.Black)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { /* Acción de aceptar */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF009E00)),
                shape = RoundedCornerShape(20.dp),
                
            ) {
                Text("Aceptar", color = Color.White)
            }
        }
    }
}