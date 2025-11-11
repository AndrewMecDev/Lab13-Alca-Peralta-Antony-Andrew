package com.example.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
import com.example.lab13.ui.theme.Lab13Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab13Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 1. ACTUALIZACIÓN: Llamamos al Composable del Ejercicio 2
                    ColorAnimationScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/**
 * Este Composable contiene la lógica para el Ejercicio 2.
 */
@Composable
fun ColorAnimationScreen(modifier: Modifier = Modifier) {

    // 1. ESTADO DE INTERCAMBIO
    // Un estado booleano simple para saber qué color mostrar.
    // 'false' = Color A, 'true' = Color B
    var isToggled by remember { mutableStateOf(false) }

    // Definimos los dos colores que queremos intercambiar
    val colorA = MaterialTheme.colorScheme.primary // Azul por defecto en Material3
    val colorB = Color(0xFF4CAF50) // Un tono de verde

    // 2. USO DE ANIMATE_COLOR_AS_STATE
    // Este Composable "observa" el 'targetValue'.
    // Si 'targetValue' cambia (de colorA a colorB), 'animateColorAsState'
    // no cambiará el color instantáneamente, sino que calculará una transición suave.
    val animatedColor by animateColorAsState(
        // El valor objetivo (target) depende de nuestro estado 'isToggled'
        targetValue = if (isToggled) colorB else colorA,

        // 3. ESPECIFICACIÓN DE ANIMACIÓN (TWEEN)
        // 'tween' (abreviatura de "in-between") es una animación basada en duración.
        // Le decimos que la transición dure 1000 milisegundos (1 segundo).
        animationSpec = tween(durationMillis = 1000),

        // --- EXPERIMENTACIÓN (OPCIONAL) ---
        // Comenta la línea de 'tween' y descomenta la siguiente para probar 'spring'.
        // 'spring' crea una animación basada en físicas (como un resorte).
        // 'dampingRatio' controla cuánta oscilación hay (menos = más rebote).
        // 'stiffness' controla la "fuerza" del resorte (más = más rápido).
        // animationSpec = spring(
        //     dampingRatio = Spring.DampingRatioMediumBouncy,
        //     stiffness = Spring.StiffnessLow
        // ),

        label = "ColorAnimation" // Una etiqueta para depuración
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Botón para alternar el estado
        Button(onClick = {
            isToggled = !isToggled
        }) {
            Text("Cambiar Color")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // El 'Box' que usa el color animado
        // Aplicamos el 'animatedColor' (que es un State<Color>)
        // directamente al 'background'.
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(animatedColor), // ¡Aquí se usa el color animado!
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "¡Mi color cambia!",
                color = MaterialTheme.colorScheme.onPrimary // Texto blanco/claro
            )
        }
    }
}

/**
 * Previsualización actualizada para el Ejercicio 2.
 */
@Preview(showBackground = true)
@Composable
fun ColorAnimationPreview() {
    Lab13Theme {
        ColorAnimationScreen()
    }
}