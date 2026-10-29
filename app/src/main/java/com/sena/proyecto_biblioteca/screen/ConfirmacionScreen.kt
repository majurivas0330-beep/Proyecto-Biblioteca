package com.sena.proyecto_biblioteca.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sena.proyecto_biblioteca.composable.BarraNavegacionSuperior
import com.sena.proyecto_biblioteca.composable.BotonPersonalizado
import com.sena.proyecto_biblioteca.composable.ContenedorTarjeta
import com.sena.proyecto_biblioteca.section.CabeceraSeccion

@Composable
fun ConfirmacionScreen(
    titulo: String,
    onNavigateToInicio: () -> Unit
) {
    Scaffold(
        topBar = {
            BarraNavegacionSuperior(
                titulo = "Confirmación",
                mostrarBotonAtras = true,
                onBackClick = onNavigateToInicio
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Success Circle
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        color = Color(0xFFE8F5E9),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Éxito",
                    tint = Color(0xFF2E7D32),
                    modifier = Modifier.size(64.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "¡Reserva Confirmada!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Tu solicitud ha sido procesada con éxito.",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(28.dp))

            CabeceraSeccion(
                titulo = "Resumen de la Reserva",
                subtitulo = "Presenta este comprobante en recepción"
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Summary Card
            ContenedorTarjeta(elevation = 4.dp) {
                ItemResumen(etiqueta = "Libro Reservado", valor = titulo, esDestacado = true)
                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = DividerDefaults.color)
                ItemResumen(etiqueta = "Código de Reserva", valor = "#RES-2026-8941")
                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = DividerDefaults.color)
                ItemResumen(etiqueta = "Estado", valor = "Pendiente de retiro")
                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = DividerDefaults.color)
                ItemResumen(etiqueta = "Plazo Máximo", valor = "48 horas hábiles")
                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = DividerDefaults.color)
                ItemResumen(etiqueta = "Punto de Entrega", valor = "Biblioteca Principal SENA")
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Return to Home Button
            BotonPersonalizado(
                texto = "Volver al Inicio",
                onClick = onNavigateToInicio,
                icono = Icons.Default.Home
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ItemResumen(
    etiqueta: String,
    valor: String,
    esDestacado: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = etiqueta,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.width(130.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = valor,
            fontSize = if (esDestacado) 15.sp else 14.sp,
            fontWeight = if (esDestacado) FontWeight.Bold else FontWeight.Medium,
            color = if (esDestacado) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )
    }
}
