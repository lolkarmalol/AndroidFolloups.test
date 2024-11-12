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
import com.example.androidfolloupstest.ui.theme.GreenTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreenTheme {
                Scaffold(
                    topBar = { LoginHeader() },
                     // Barra verde en la parte inferior
                    content = { paddingValues ->
                        LoginScreen(paddingValues)
                    }
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Spacer(modifier = Modifier.height(5.dp))

        // Barra verde debajo del header
        Navigation()  // Barra verde ubicada debajo del LoginHeader

         // Espacio entre la barra verde y el contenido del login

        // Welcome text with image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)  // Adjusted height to accommodate image
                .background(Color.White, shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp)
            ) {
                // Image on the left side
                Image(
                    painter = painterResource(R.drawable.img),
                    contentDescription = "Carusel Image",
                    modifier = Modifier.size(230.dp)
                )

                // Column with text on the right side
                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "ETAPA",
                        color = Color(0xFF009E00),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "PRODUCTIVA",
                        color = Color(0xFF003366),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Login form
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
                    modifier = Modifier.align(Alignment.CenterHorizontally) // Centered text
                        .padding(bottom = 20.dp)
                )

                // Email input
                InputField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Correo electrónico",
                    icon = painterResource(R.drawable.aprendiz)
                )

                // Password input
                InputField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Contraseña",
                    icon = painterResource(R.drawable.contra), // Cambié el ícono a uno adecuado para contraseñas
                    isPassword = true,
                    showPassword = showPassword,
                    onPasswordVisibilityChanged = { showPassword = it }
                )

                // Login button
                Button(
                    onClick = { /* Handle login action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009E00))
                ) {
                    Text(text = "Iniciar Sesión", color = Color.White)
                }

                // Forgot password link
                Spacer(modifier = Modifier.height(20.dp))
                TextButton(
                    onClick = { /* Navigate to Forgot password screen */ },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "¿Olvidaste tu contraseña?", color = Color(0xFF003366))
                }
            }
        }
    }
}

@Composable
fun LoginHeader() {
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
fun Navigation() {
    Box(
        modifier = Modifier
            .fillMaxWidth()  // Asegura que ocupe todo el ancho de la pantalla
            .height(45.dp)  // Aumenta la altura para que sea visible
            .background(Color(0xFF009E00))  // Color de la barra verde
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
            Image(
                painter = icon,
                contentDescription = label,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            if (isPassword) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
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
