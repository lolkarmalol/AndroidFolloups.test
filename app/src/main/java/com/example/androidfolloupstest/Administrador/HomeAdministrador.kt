package com.example.androidfolloupstest.Administrador

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.androidfolloupstest.R

class HomeAdministrador : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection()
            NotificationBar()
            ButtonGrid()
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
        val context = LocalContext.current
        val userName = "Paula Valencia"
        val userRole = "Administrador"

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
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = userName, style = MaterialTheme.typography.h6)
                    Text(text = userRole, style = MaterialTheme.typography.body2)
                }

                DropdownMenuItem(onClick = {
                    expanded = false
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
                        val intent = Intent(context, NotificacionesActivity::class.java)
                        context.startActivity(intent)
                    },
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }

    @Composable
    fun ButtonGrid() {
        val context = LocalContext.current
        Column(modifier = Modifier.padding(16.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ActionButton("Instructores", R.drawable.instructor_icono) {
                    Log.d("MainActivity", "Instructores button clicked")
                    context.startActivity(Intent(context, InstructorActivity::class.java))
                }
                ActionButton("Aprendices", R.drawable.aprendiz_icono) {
                    Log.d("MainActivity", "Aprendices button clicked")
                    context.startActivity(Intent(context, ApprenticeActivity::class.java))
                }

                PlantillasDropdownButton()
            }
        }
    }

    @Composable
    fun PlantillasDropdownButton() {
        val expanded = remember { mutableStateOf(false) }
        val subMenuExpanded = remember { mutableStateOf(false) }
        val context = LocalContext.current

        Box(
            modifier = Modifier
                .size(300.dp, 150.dp)
                .padding(8.dp)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp), clip = false)
        ) {
            Button(
                onClick = { expanded.value = !expanded.value },
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.template_icon),
                        contentDescription = "Plantillas",
                        modifier = Modifier.size(40.dp)
                    )
                    Text(text = "Plantillas")
                }
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.width(200.dp)
            ) {
                DropdownMenuItem(onClick = {
                    expanded.value = false
                    context.startActivity(Intent(context, PasantiaActivity::class.java))
                }) {
                    Text("Pasantía")
                }
                DropdownMenuItem(onClick = {
                    expanded.value = false
                    context.startActivity(Intent(context, VinculoLaboralActivity::class.java))
                }) {
                    Text("Vínculo Laboral")
                }

                DropdownMenuItem(onClick = {
                    subMenuExpanded.value = !subMenuExpanded.value
                }) {
                    Text("Contrato de Aprendizaje")
                }

                if (subMenuExpanded.value) {
                    DropdownMenuItem(onClick = {
                        expanded.value = false
                        subMenuExpanded.value = false
                        context.startActivity(Intent(context, VerPlantillaActivity::class.java))
                    }) {
                        Text("Ver Plantilla")
                    }
                    DropdownMenuItem(onClick = {
                        expanded.value = false
                        subMenuExpanded.value = false
                        // Añadir lógica para añadir una plantilla
                    }) {
                        Text("+ Añadir Plantilla")
                    }
                }

                DropdownMenuItem(onClick = {
                    expanded.value = false
                    context.startActivity(Intent(context, UnidadProductivaActivity::class.java))
                }) {
                    Text("Unidad Productiva Familiar")
                }
            }
        }
    }

    @Composable
    fun ActionButton(text: String, iconRes: Int, onClick: () -> Unit) {
        Box(
            modifier = Modifier
                .size(300.dp, 150.dp)
                .padding(8.dp)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp), clip = false)
        ) {
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = text,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(text = text)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}
