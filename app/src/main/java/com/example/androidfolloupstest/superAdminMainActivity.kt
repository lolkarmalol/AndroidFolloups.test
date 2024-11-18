package com.example.androidfolloupstest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.androidfolloupstest.ui.theme.GreenTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreenTheme {
                EtapaProductivaScreen()
            }
        }
    }

    fun navigateToLogin(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun EtapaProductivaScreen() {
    val context = LocalContext.current
    var selectedSection by rememberSaveable { mutableStateOf("Introducción") }
    var showDialog by remember { mutableStateOf(false) }
    var modalInfo by remember { mutableStateOf("") }

    Scaffold(
        topBar = { Header() },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Navigation(
                    onNavigationClick = { section -> selectedSection = section },
                    onLoginClick = { (context as? MainActivity)?.navigateToLogin(context) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item { CarouselSection() }
                    item {
                        ContentSections(
                            selectedSection = selectedSection,
                            showDialog = showDialog,
                            modalInfo = modalInfo,
                            onModeSelected = { mode ->
                                modalInfo = getModeInfo(mode)
                                showDialog = true
                            }
                        )
                    }
                }
            }
        }
    )

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Información de Modalidad") },
            text = { Text(text = modalInfo) },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cerrar")
                }
            }
        )
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.padding(10.dp),
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
    }
}

@Composable
fun Navigation(onNavigationClick: (String) -> Unit, onLoginClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFF009E00))
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationButton("¿Qué es Etapa Productiva?", onClick = { onNavigationClick("¿Qué es Etapa Productiva?") })
            NavigationButton("Tipos de Modalidad", onClick = { onNavigationClick("Tipos de Modalidad") })
            NavigationButton("Misión", onClick = { onNavigationClick("Misión") })
        }

        Button(
            onClick = { onLoginClick() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007A00)),
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text("Iniciar Sesión", color = Color.White)
        }
    }
}

@Composable
fun NavigationButton(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        color = Color.White,
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 8.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
}

@Composable
fun ContentSections(
    selectedSection: String,
    showDialog: Boolean,
    modalInfo: String,
    onModeSelected: (String) -> Unit
) {
    when (selectedSection) {
        "¿Qué es Etapa Productiva?" -> {
            ContentSection(
                title = "¿Qué es Etapa Productiva?",
                text = "La 'Etapa Productiva' es una fase del proceso formativo en la que los aprendices aplican en un entorno laboral real los conocimientos adquiridos durante la \"Etapa Lectiva\" o teórica. Esta etapa, similar a una práctica o pasantía, permite que el aprendiz gane experiencia directa en su campo de estudio bajo supervisión. Su objetivo es desarrollar competencias laborales, adquirir experiencia en el entorno real de trabajo, contribuir a la productividad de la empresa y facilitar la inserción laboral, ya que en muchos casos las empresas terminan contratando a los aprendices."
            )
        }
        "Tipos de Modalidad" -> {
            ContentSection(
                title = "Tipos de Modalidad",
                text = "Cada modalidad de la etapa productiva está diseñada para adaptarse a las distintas necesidades de formación y las características del entorno laboral, permitiendo que los aprendices adquieran experiencia según sus intereses y las oportunidades disponibles. A continuación, se describen las modalidades más comunes, cada una con sus beneficios y características principales:"
            )
            ModeTable(onModeSelected = onModeSelected)
        }
        "Misión" -> {
            ContentSection(
                title = "Misión",
                text = "La misión de la Etapa Productiva es promover la integración efectiva de los estudiantes en el entorno laboral, brindándoles la oportunidad de aplicar y perfeccionar los conocimientos adquiridos durante su formación académica. Este proceso se centra en fortalecer sus habilidades técnicas y desarrollar competencias transversales que les permitan adaptarse y responder a las demandas cambiantes del mercado laboral. A través de experiencias prácticas supervisadas y orientadas, buscamos no solo mejorar la formación integral de los estudiantes, sino también impulsar su crecimiento personal y profesional. Nuestro objetivo es formar individuos competentes, comprometidos y preparados para enfrentar los desafíos de un entorno laboral globalizado, contribuyendo así al desarrollo y productividad de las organizaciones que los reciben, y creando un impacto positivo en la sociedad."
            )
        }
        else -> {
            ContentSection(
                title = "¿Qué es Etapa Productiva?",
                text = "La 'Etapa Productiva' es una fase del proceso formativo en la que los aprendices aplican en un entorno laboral real los conocimientos adquiridos durante la \"Etapa Lectiva\" o teórica. Esta etapa, similar a una práctica o pasantía, permite que el aprendiz gane experiencia directa en su campo de estudio bajo supervisión. Su objetivo es desarrollar competencias laborales, adquirir experiencia en el entorno real de trabajo, contribuir a la productividad de la empresa y facilitar la inserción laboral, ya que en muchos casos las empresas terminan contratando a los aprendices."
            )
        }
    }
}

@Composable
fun ContentSection(title: String, text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF009E00),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselSection() {
    val images = listOf(
        R.drawable.carrusel1,
        R.drawable.carrusel2,
        R.drawable.carrusel3,
        R.drawable.carrusel4
    )
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = pagerState.currentPage) {
        delay(3000)
        val nextPage = (pagerState.currentPage + 1) % images.size
        coroutineScope.launch {
            pagerState.animateScrollToPage(nextPage)
        }
    }

    HorizontalPager(
        count = images.size,
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(10.dp)
    ) { page ->
        Image(
            painter = painterResource(images[page]),
            contentDescription = "Carousel Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ModeTable(onModeSelected: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Pasantía",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .clickable { onModeSelected("Pasantía") }
                .padding(8.dp)
                .fillMaxWidth()
                .shadow(1.dp, RoundedCornerShape(8.dp))
                .background(Color(0xFF009E00))
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Contrato de Aprendizaje",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .clickable { onModeSelected("Contrato de Aprendizaje") }
                .padding(8.dp)
                .fillMaxWidth()
                .shadow(1.dp, RoundedCornerShape(8.dp))
                .background(Color(0xFF009E00))
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Vínculo Laboral",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .clickable { onModeSelected("Vínculo Laboral") }
                .padding(8.dp)
                .fillMaxWidth()
                .shadow(1.dp, RoundedCornerShape(8.dp))
                .background(Color(0xFF009E00))
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Unidad Productiva Familiar",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .clickable { onModeSelected("Unidad Productiva Familiar") }
                .padding(8.dp)
                .fillMaxWidth()
                .shadow(1.dp, RoundedCornerShape(8.dp))
                .background(Color(0xFF009E00))
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Proyecto Productivo Empresarial",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .clickable { onModeSelected("Proyecto Productivo Empresarial") }
                .padding(8.dp)
                .fillMaxWidth()
                .shadow(1.dp, RoundedCornerShape(8.dp))
                .background(Color(0xFF009E00))
                .padding(16.dp)
        )
    }
}

fun getModeInfo(mode: String): String {
    return when (mode) {
        "Pasantía" -> "La pasantía permite al aprendiz realizar prácticas en una empresa, poniendo en práctica lo aprendido en su formación académica. Ideal para estudiantes que desean experimentar el entorno laboral y aplicar sus conocimientos."
        "Contrato de Aprendizaje" -> "El contrato de aprendizaje combina la formación teórica con la práctica laboral, donde el aprendiz es un colaborador activo en la empresa, y recibe una remuneración acorde al tipo de contrato."
        "Vínculo Laboral" -> "En el vínculo laboral, el aprendiz trabaja directamente en una empresa con un contrato formal de trabajo y salario acorde a las normativas laborales, permitiendo una inmersión total en el entorno laboral."
        "Unidad Productiva Familiar" -> "En la modalidad de unidad productiva familiar, el aprendiz se involucra en el desarrollo de un emprendimiento familiar, aplicando sus conocimientos para fortalecer y expandir el negocio."
        "Proyecto Productivo Empresarial" -> "El proyecto productivo empresarial permite a los aprendices participar en proyectos innovadores dentro de las empresas, generando soluciones prácticas para las necesidades de la empresa."
        else -> "Información no disponible."
    }
}