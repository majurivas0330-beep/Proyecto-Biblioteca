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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.PublishedWithChanges
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sena.proyecto_biblioteca.ui.theme.composable.BarraNavegacionSuperior
import com.sena.proyecto_biblioteca.ui.theme.composable.BotonPersonalizado
import com.sena.proyecto_biblioteca.ui.theme.composable.ContenedorTarjeta
import com.sena.proyecto_biblioteca.ui.theme.section.CabeceraSeccion

@Composable
fun RenovarReservaScreen(
    nombreLibro: String,
    codigoReserva: String,
    fechaReserva: String,
    onNavigateToExitosa: (nombreLibro: String, codigoReserva: String, fechaNueva: String) -> Unit,
    onBackClick: () -> Unit
) {
    var fechaNuevaEditable by remember { mutableStateOf("30/06/2026") }

    Scaffold(
        topBar = {
            BarraNavegacionSuperior(
                titulo = "Renovar Reserva",
                mostrarBotonAtras = true,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Compact Book Card
            ContenedorTarjeta(elevation = 3.dp) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.MenuBook,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = nombreLibro,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Código: $codigoReserva",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            CabeceraSeccion(
                titulo = "Extensión de Préstamo",
                subtitulo = "Ingresa la nueva fecha límite deseada"
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Date Fields Card
            ContenedorTarjeta(elevation = 3.dp) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Current Date Readonly Field
                    Text(
                        text = "Reserva Actual",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = fechaReserva,
                        onValueChange = {},
                        readOnly = true,
                        enabled = false,
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Event,
                                contentDescription = null
                            )
                        },
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // New Date Editable Field
                    Text(
                        text = "Nueva Fecha",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = fechaNuevaEditable,
                        onValueChange = { fechaNuevaEditable = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Fecha editable") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Orange Action Button
            BotonPersonalizado(
                texto = "Confirmar Renovación",
                onClick = {
                    onNavigateToExitosa(nombreLibro, codigoReserva, fechaNuevaEditable)
                },
                icono = Icons.Default.PublishedWithChanges,
                colorFondo = Color(0xFFE65100), // Distinctive Orange Color
                colorTexto = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
