package com.example.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab13.ui.theme.Lab13Theme // Android Studio genera esto automáticamente

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita el modo de borde a borde
        setContent {
            // Lab13Theme es el tema de tu aplicación, generado por Android Studio
            Lab13Theme {
                // Scaffold proporciona una estructura básica de Material Design
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Llamamos a nuestro Composable del ejercicio
                    VisibilityAnimationScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/**
 * Este Composable contiene la lógica para el Ejercicio 1.
 */
@Composable
fun VisibilityAnimationScreen(modifier: Modifier = Modifier) {

    // 1. CREACIÓN DEL ESTADO DE VISIBILIDAD
    // Usamos 'remember' para que el estado 'isVisible' sobreviva a las recomposiciones.
    // Usamos 'mutableStateOf(true)' para inicializar el estado como visible.
    // 'by' es un delegado de propiedad de Kotlin que simplifica el acceso a .value
    var isVisible by remember { mutableStateOf(true) }

    // Column organiza sus hijos verticalmente.
    Column(
        // 'modifier' nos permite configurar el Composable
        modifier = modifier
            .fillMaxSize() // Ocupa todo el espacio disponible
            .padding(16.dp), // Añade padding alrededor
        // Centra el contenido horizontalmente en la columna
        horizontalAlignment = Alignment.CenterHorizontally,
        // Centra el contenido verticalmente en la columna
        verticalArrangement = Arrangement.Center
    ) {

        // 2. BOTÓN PARA ALTERNAR LA VISIBILIDAD
        Button(onClick = {
            // En el evento 'onClick', invertimos el valor booleano del estado.
            // Si era true -> false, si era false -> true.
            isVisible = !isVisible
        }) {
            // El texto del botón cambia según el estado 'isVisible'
            Text(if (isVisible) "Ocultar" else "Mostrar")
        }

        // Un espacio vertical para separar el botón del cuadro
        Spacer(modifier = Modifier.height(24.dp))

        // 3. ANIMATED_VISIBILITY
        // Este es el Composable clave. Su contenido solo se mostrará
        // si el parámetro 'visible' es true.
        AnimatedVisibility(
            visible = isVisible,
            // 4. CONFIGURACIÓN DE ANIMACIONES (Como se pidió)
            // 'enter' define cómo aparece el elemento
            enter = fadeIn(
                // Opcional: puedes ajustar la duración de la animación
                // animationSpec = tween(durationMillis = 1000)
            ),
            // 'exit' define cómo desaparece el elemento
            exit = fadeOut(
                // Opcional: puedes ajustar la duración de la animación
                // animationSpec = tween(durationMillis = 1000)
            )
        ) {
            // CONTENIDO A ANIMAR:
            // Este es el elemento que aparecerá y desaparecerá.
            Box(
                modifier = Modifier
                    .size(200.dp) // Un tamaño fijo de 200x200 dp
                    .background(MaterialTheme.colorScheme.primary), // Color primario del tema
                // Centra el texto dentro del Box
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "¡Aquí estoy!",
                    color = MaterialTheme.colorScheme.onPrimary // Color de texto legible sobre el primario
                )
            }
        }
    }
}

/**
 * Una función de Previsualización para ver nuestro Composable en el editor
 * de Android Studio sin tener que ejecutar la app.
 */
@Preview(showBackground = true)
@Composable
fun VisibilityAnimationPreview() {
    Lab13Theme {
        VisibilityAnimationScreen()
    }
}