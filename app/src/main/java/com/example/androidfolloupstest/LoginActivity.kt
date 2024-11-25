package com.example.androidfolloupstest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import androidx.test.core.app.ApplicationProvider
import com.example.androidfolloupstest.network.ApiClient
import com.example.androidfolloupstest.network.LoginRequest
import com.example.androidfolloupstest.ui.theme.GreenTheme
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreenTheme {
                Scaffold(
                    topBar = { LoginHeader() },
                    content = { paddingValues -> LoginScreen(paddingValues) }
                )
            }
        }
    }
}

@Composable
fun LoginScreen(paddingValues: PaddingValues) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Spacer(modifier = Modifier.height(5.dp))

        Navigation()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(Color.White, shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp)
            ) {
                Image(painter = painterResource(R.drawable.img), contentDescription = "Carusel Image", modifier = Modifier.size(230.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    Text(text = "ETAPA", color = Color(0xFF009E00), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "PRODUCTIVA", color = Color(0xFF003366), fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.9f), shape = RoundedCornerShape(12.dp))
                    .shadow(4.dp)
                    .padding(20.dp)
            ) {
                Text(
                    text = "USUARIO",
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF003366)),
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 20.dp)
                )

                InputField(value = email, onValueChange = { email = it }, label = "Correo electrónico", icon = painterResource(R.drawable.aprendiz))
                InputField(value = password, onValueChange = { password = it }, label = "Contraseña", icon = painterResource(R.drawable.contra), isPassword = true, showPassword = showPassword, onPasswordVisibilityChanged = { showPassword = it })

                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
                }

                Button(
                    onClick = {
                        isLoading = true
                        coroutineScope.launch {
                            val response = ApiClient.authService.login(LoginRequest(email, password))
                            if (response.isSuccessful) {
                                val token = response.body()?.token
                                token?.let { saveAuthToken(ApplicationProvider.getApplicationContext(), it) }
                                isLoading = false
                            } else {
                                errorMessage = "Credenciales incorrectas"
                                isLoading = false
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009E00))
                ) {
                    Text(text = if (isLoading) "Cargando..." else "Iniciar Sesión", color = Color.White)
                }

                Spacer(modifier = Modifier.height(20.dp))
                TextButton(onClick = { /* Navegar a la pantalla de "Olvidé mi contraseña" */ }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(text = "¿Olvidaste tu contraseña?", color = Color(0xFF003366))
                }
            }
        }
    }
}

@Composable
fun LoginHeader() {
    Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.Top) {
        Image(painter = painterResource(id = R.drawable.logo_sena), contentDescription = "Logo SENA", modifier = Modifier.size(70.dp))
        Spacer(modifier = Modifier.width(10.dp))
        Image(painter = painterResource(id = R.drawable.logo_etapaproductiva), contentDescription = "Logo Etapa Productiva", modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text("Etapa\nProductiva", fontSize = 13.sp, color = Color(0xFF009E00), modifier = Modifier.padding(top = 6.dp).offset(x = (-5).dp))
            Spacer(modifier = Modifier.height(15.dp))
            Text("Centro de Comercio y Servicios", fontSize = 14.sp, color = Color(0xFF009E00), modifier = Modifier.offset(x = (-30).dp))
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun Navigation() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(Color(0xFF009E00))
    )
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: Painter,
    isPassword: Boolean = false,
    showPassword: Boolean = false,
    onPasswordVisibilityChanged: (Boolean) -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = icon, contentDescription = label, modifier = Modifier.size(22.dp))
            Spacer(modifier = Modifier.width(10.dp))

            if (isPassword) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { /* Hide keyboard */ }),
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 16.sp, color = Color.Black)
                )
            } else {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { /* Hide keyboard */ }),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 16.sp, color = Color.Black)
                )
            }
        }
    }
}
