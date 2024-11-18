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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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

class TemplateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "agregar_instructor") {
                    composable("agregar_instructor") { AgregarInstructorScreen(navController) }

            }
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
                // Lista de etiquetas de los campos
                val labels = listOf(
                    "Nombre", "Programa", "Cédula", "Correo", "Celular",
                    "Ficha", "Tipo de contrato",
                    "Inicio Contrato", "Fin Contrato",
                    "Nit Empresa", "Razon Social", "Direccion",
                    "Telefono Empresa", "Nombre Instructor", "Correo Instructor", "Enviar Aprendiz", "Enviar Instructor"
                )

                // Estado para el programa
                var selectedProgram by remember { mutableStateOf("") }
                var expanded by remember { mutableStateOf(false) }

                var selectedContractType by remember { mutableStateOf("") }
                var contractExpanded by remember { mutableStateOf(false) }

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

                        // Aquí agregamos el dropdown para el "Programa"
                        if (label == "Programa") {
                            Column(
                                modifier = Modifier
                                    .weight(2f)
                                    .height(50.dp)
                            ) {
                                TextField(
                                    value = selectedProgram,
                                    onValueChange = { /* No se permite editar directamente el campo */ },
                                    readOnly = true,
                                    placeholder = { Text("Seleccione Programa") },
                                    modifier = Modifier.fillMaxWidth(),
                                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                                    trailingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.ArrowDropDown,
                                            contentDescription = null,
                                            modifier = Modifier.clickable { expanded = !expanded }
                                        )
                                    }
                                )

                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    // Lista de programas
                                    val programOptions = listOf(
                                        "GESTION ADMINISTRATIVA DEL SECTOR SALUD", "GESTION DE MERCADOS", "ASISTENCIA ADMINISTRATIVA",
                                        "GESTION DE PROCESOS ADMINISTRATIVOS DE SALUD", "GESTION EMPRESARIAL", "GUIANZA TURISTICA",
                                        "GESTION CONTABLE Y FINANCIERA", "ANALISIS Y DESARROLLO DE SISTEMAS DE INFORMACION",
                                        "GESTION LOGISTICA", "NEGOCIACION INTERNACIONAL"
                                    )

                                    programOptions.forEach { option ->
                                        DropdownMenuItem(onClick = {
                                            selectedProgram = option
                                            expanded = false
                                        }) {
                                            Text(option)
                                        }
                                    }
                                }
                            }
                        } else if (label == "Tipo de contrato") {
                            Column(
                                modifier = Modifier
                                    .weight(2f)
                                    .height(50.dp)
                            ) {
                                TextField(
                                    value = selectedContractType,
                                    onValueChange = { /* No se permite editar directamente el campo */ },
                                    readOnly = true,
                                    placeholder = { Text("Seleccione Tipo de contrato") },
                                    modifier = Modifier.fillMaxWidth(),
                                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                                    trailingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.ArrowDropDown,
                                            contentDescription = null,
                                            modifier = Modifier.clickable { contractExpanded = !contractExpanded }
                                        )
                                    }
                                )

                                DropdownMenu(
                                    expanded = contractExpanded,
                                    onDismissRequest = { contractExpanded = false }
                                ) {
                                    // Lista de tipos de contrato
                                    val contractOptions = listOf("N/A", "Indefinido", "Definido", "Por horas")

                                    contractOptions.forEach { option ->
                                        DropdownMenuItem(onClick = {
                                            selectedContractType = option
                                            contractExpanded = false
                                        }) {
                                            Text(option)
                                        }
                                    }
                                }
                            }
                        } else {
                            TextField(
                                value = "",
                                onValueChange = {},
                                modifier = Modifier.weight(2f),
                                placeholder = { Text(label) },
                            )
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
            AgregarInstructorScreen(navController = rememberNavController())

    }
}

