package com.example.androidfolloupstest.Aprendice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.androidfolloupstest.R

@Composable
fun HomeScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = 30.dp)
    ) {
        // Primer LinearLayout en horizontal
        Row  (
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
            Box( modifier = Modifier
                .fillMaxWidth()  // Ocupa todo el ancho de la pantalla

                .wrapContentSize(Alignment.TopEnd),
                contentAlignment = Alignment.CenterStart

            ) {

                Text(
                    text = "Dayana",
                    modifier = Modifier
                        .background(Color(0xFFFFFFFF))
                        .shadow(4.dp, RoundedCornerShape(20.dp))
                        .padding(10 .dp)
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
                        fontWeight = FontWeight.Bold,
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
            }}
        InstructorContainer()
        //bitacora
        Surface(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .padding(vertical = 8.dp)
                .background(Color(0xFFF7F7F7)),
            shape = RoundedCornerShape(12.dp),

            // Color de fondo similar a bg-gray-100
            shadowElevation = 4.dp


        ) {
            Column(
                modifier = Modifier.padding(16.dp), // Padding interno
                horizontalAlignment = Alignment.CenterHorizontally, // Alinear horizontalmente
                verticalArrangement = Arrangement.Center // Alinear verticalmente
            ) {
                Text(
                    text = "Bitácoras",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 8.dp) // Margen inferior
                )

                // Contenedor para el gráfico (simulación)
                Box(
                    modifier = Modifier
                        .size(240.dp) // Tamaño del gráfico
                        .background(Color.White), // Color de fondo del contenedor
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bitacora),
                        contentDescription = null,
                        modifier = Modifier.size(90.dp)
                    )
                }
            }

        }
        TimelineContainer()
    }
}






// Función para el contenedor del instructor
@Composable
fun InstructorContainer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        // Card para el instructor asignado
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Color(0xFFF7F7F7))// Similar a bg-gray-100
                .shadow(4.dp) // Sin paréntesis

        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Instructor Asignado",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Column {
                    // Lista de detalles del instructor
                    DetailItem(label = "Nombre:", value = "Mariany Dorado")
                    DetailItem(label = "Correo:", value = "edusena10@gmail.com")
                    DetailItem(label = "Teléfono:", value = "322 546 78 67")
                }
            }
        }
    }
}

// Composable para mostrar los detalles del instructor
@Composable
fun DetailItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = "$label $value", fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp)) // Espaciado entre los items
        // Divider (simulando el hr)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White)
        )
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
        shadowElevation = 4.dp
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
