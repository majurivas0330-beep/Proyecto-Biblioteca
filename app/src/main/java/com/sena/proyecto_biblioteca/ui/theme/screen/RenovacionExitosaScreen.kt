package com.sena.proyecto_biblioteca.ui.theme.screen

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
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.material.icons.filled.CheckCircle
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
import com.sena.proyecto_biblioteca.ui.theme.composable.BarraNavegacionSuperior
import com.sena.proyecto_biblioteca.ui.theme.composable.BotonPersonalizado
import com.sena.proyecto_biblioteca.ui.theme.composable.ContenedorTarjeta
import com.sena.proyecto_biblioteca.ui.theme.section.CabeceraSeccion

@Composable
fun RenovacionExitosaScreen(
    nombreLibro: String,
    codigoReserva: String,
    fechaNueva: String,
    onVolverAMisReservas: () -> Unit
) {
    Scaffold(
        topBar = {
            BarraNavegacionSuperior(
                titulo = "Renovación Exitosa",
                mostrarBotonAtras = false
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

            // Green Success Icon Container
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
                text = "Reserva Renovada",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "La fecha límite de devolución se ha actualizado correctamente.",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(28.dp))

            CabeceraSeccion(
                titulo = "Resumen de Renovación",
                subtitulo = "Comprobante de actualización"
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Summary Card
            ContenedorTarjeta(elevation = 4.dp) {
                FilaResumen(etiqueta = "Libro", valor = nombreLibro, esDestacado = true)
                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = DividerDefaults.color)
                FilaResumen(etiqueta = "Código", valor = codigoReserva)
                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = DividerDefaults.color)
                FilaResumen(etiqueta = "Nueva Fecha", valor = fechaNueva, esDestacado = true)
                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = DividerDefaults.color)
                FilaResumen(etiqueta = "Estado", valor = "Renovada Exitosamente")
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Green Return Button
            BotonPersonalizado(
                texto = "Volver a Mis Reservas",
                onClick = onVolverAMisReservas,
                icono = Icons.AutoMirrored.Filled.FormatListBulleted,
                colorFondo = Color(0xFF2E7D32), // Green Button
                colorTexto = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun FilaResumen(
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
            modifier = Modifier.width(110.dp)
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
