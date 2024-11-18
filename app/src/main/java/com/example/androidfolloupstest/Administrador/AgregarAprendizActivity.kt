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


class AgregarAprendizActivity: ComponentActivity(){
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
                                        "GESTION LOGISTICA", "NEGOCIACION INTERNACIONAL", "GESTION DEL TALENTO HUMANO",
                                        "GESTION DOCUMENTAL", "CONTABILIZACION DE OPERACIONES COMERCIALES Y FINANCIERAS",
                                        "GESTION BANCARIA Y DE ENTIDADES FINANCIERAS", "PELUQUERIA", "PANIFICACION", "COCINA",
                                        "SERVICIOS FARMACEUTICOS", "SALUD PUBLICA", "APOYO ADMINISTRATIVO EN SALUD",
                                        "OPERACION TURISTICA LOCAL", "ANIMACION 3D", "ANIMACION DIGITAL", "PROMOCION DE PRODUCTOS",
                                        "SERVICIOS Y OPERACIONES MICROFINANCIERAS", "CUIDADO ESTETICO DE MANOS Y PIES",
                                        "CONTROL DE MOVILIDAD TRANSPORTE Y SEGURIDAD VIAL", "ENFERMERIA", "SISTEMAS",
                                        "DISTRIBUCION FISICA INTERNACIONAL", "ASESORIA COMERCIAL Y OPERACIONES DE ENTIDADES FINANCIERAS",
                                        "ATENCION INTEGRAL A LA PRIMERA INFANCIA", "ASISTENCIA EN ORGANIZACION DE ARCHIVOS",
                                        "DESARROLLO DE OPERACIONES LOGISTICA EN LA CADENA DE ABASTECIMIENTO", "SERVICIO DE RESTAURANTE Y BAR",
                                        "OPERACIONES DE COMERCIO EXTERIOR", "DISEÑO E INTEGRACION DE MULTIMEDIA",
                                        "INFORMACION Y SERVICIO AL CLIENTE", "SERVICIOS DE AGENCIAS DE VIAJES", "ASESORIA COMERCIAL",
                                        "PROCESOS DE PANADERIA", "GESTION COMUNITARIA DEL RIESGO DE DESASTRES",
                                        "PROGRAMACION DE APLICACIONES Y SERVICIOS PARA LA NUBE", "PROGRAMACION DE SOFTWARE",
                                        "SERVICIOS DE BARISMO", "GESTION CONTABLE Y DE INFORMACION FINANCIERA",
                                        "INTEGRACION DE OPERACIONES LOGISTICAS", "INTEGRACION DE CONTENIDOS DIGITALES", "AUXILIAR EN COCINA",
                                        "PROGRAMACION PARA ANALITICA DE DATOS", "AGENTE DE TRANSITO Y TRANSPORTE", "ANALISIS Y DESARROLLO DE SOFTWARE",
                                        "ATENCION INTEGRAL AL CLIENTE", "CONTROL DE MOVILIDAD, TRANSPORTE Y SEGURIDAD VIAL",
                                        "DESARROLLO DE PROCESOS DE MERCADEO", "DESARROLLO PUBLICITARIO", "GESTION INTEGRAL DEL TRANSPORTE",
                                        "ORGANIZACION DE ARCHIVO", "PRESELECCION DE TALENTO HUMANO MEDIADO POR HERRAMIENTAS TIC",
                                        "SERVICIOS EN CONTACT CENTER Y BPO", "COORDINACION DE PROCESOS LOGISTICOS"
                                    )

                                    programOptions.forEach { program ->
                                        DropdownMenuItem(onClick = {
                                            selectedProgram = program
                                            expanded = false
                                        }) {
                                            Text(text = program)
                                        }
                                    }
                                }
                            }
                        } else if (label == "Tipo de contrato") { // Dropdown para "Tipo de contrato"
                            Column(
                                modifier = Modifier
                                    .weight(2f)
                                    .height(50.dp)
                            ) {
                                TextField(
                                    value = selectedContractType,
                                    onValueChange = { /* No se permite editar directamente el campo */ },
                                    readOnly = true,
                                    placeholder = { Text("Seleccione Tipo de Contrato") },
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
                                    val contractOptions = listOf(
                                        "Contrato de Aprendizaje", "Pasantía", "Vínculo Laboral",
                                        "Patrocinio", "Unidad Productiva", "Proyecto Productivo"
                                    )

                                    contractOptions.forEach { contractType ->
                                        DropdownMenuItem(onClick = {
                                            selectedContractType = contractType
                                            contractExpanded = false
                                        }) {
                                            Text(text = contractType)
                                        }
                                    }
                                }
                            }
                        } else {
                            // Otros campos de texto
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
    }
    @Composable
    fun ActionButtons(navController: NavController) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Añadimos padding para separación
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                // Fila para "Aprendiz"
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { /* Acción para el primer ícono */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.mensaje), // Carga la imagen del drawable
                            contentDescription = "Mensaje Aprendiz",
                            tint = Color.Unspecified, // Desactiva el color de tintado por defecto
                            modifier = Modifier
                                .size(24.dp)
                                .background(Color.Transparent) // Asegura fondo transparente
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp)) // Espacio entre ícono y texto
                    Text("Aprendiz")
                }

                // Fila para "Instructor"
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { /* Acción para el segundo ícono */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.mensaje), // Carga la imagen del drawable
                            contentDescription = "Mensaje Instructor",
                            tint = Color.Unspecified, // Desactiva el color de tintado por defecto
                            modifier = Modifier
                                .size(24.dp)
                                .background(Color.Transparent) // Asegura fondo transparente
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp)) // Espacio entre ícono y texto
                    Text("Instructor")
                }
            }

            Button(
                onClick = {
                    // Lógica de confirmar
                    navController.navigate("instructor") // Ejemplo de navegación
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF009E00)), // Color verde
                modifier = Modifier.size(150.dp, 50.dp) // Tamaño del botón
            ) {
                Text("Confirmar", color = Color.White) // Texto en blanco
            }
        }
    }



    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
            AgregarInstructorScreen(rememberNavController())
        }

}


