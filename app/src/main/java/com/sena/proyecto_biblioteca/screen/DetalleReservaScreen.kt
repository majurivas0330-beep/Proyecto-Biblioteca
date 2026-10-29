package com.sena.proyecto_biblioteca.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sena.proyecto_biblioteca.composable.BarraNavegacionSuperior
import com.sena.proyecto_biblioteca.composable.BotonPersonalizado
import com.sena.proyecto_biblioteca.composable.ContenedorTarjeta
import com.sena.proyecto_biblioteca.section.CabeceraSeccion

@Composable
fun DetalleReservaScreen(
    nombreLibro: String,
    autor: String,
    fechaReserva: String,
    codigoReserva: String,
    estado: String,
    onNavigateToRenovar: (nombreLibro: String, codigoReserva: String, fechaReserva: String) -> Unit,
    onCancelarReserva: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            BarraNavegacionSuperior(
                titulo = "Detalle de Reserva",
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
            // Header Book Summary Card
            ContenedorTarjeta(elevation = 4.dp) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.MenuBook,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(34.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = nombreLibro,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Autor: $autor",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            CabeceraSeccion(
                titulo = "Información de la Reserva",
                subtitulo = "Parámetros recibidos de la vista anterior"
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Details List Card
            ContenedorTarjeta(elevation = 3.dp) {
                FilaDetalle(etiqueta = "Libro", valor = nombreLibro)
                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = DividerDefaults.color)
                FilaDetalle(etiqueta = "Autor", valor = autor)
                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = DividerDefaults.color)
                FilaDetalle(etiqueta = "Fecha Reserva", valor = fechaReserva)
                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = DividerDefaults.color)
                FilaDetalle(etiqueta = "Código", valor = codigoReserva)
                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = DividerDefaults.color)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Estado",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.width(110.dp)
                    )
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFE8F5E9)
                    ) {
                        Text(
                            text = estado,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32),
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Informative Box (Cuadro Informativo)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.tertiaryContainer
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Información",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Estos datos fueron enviados desde la pantalla anterior como parámetros.",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Action Buttons
            BotonPersonalizado(
                texto = "Renovar Reserva",
                onClick = {
                    onNavigateToRenovar(nombreLibro, codigoReserva, fechaReserva)
                },
                icono = Icons.Default.Update,
                colorFondo = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))

            BotonPersonalizado(
                texto = "Cancelar Reserva",
                onClick = onCancelarReserva,
                icono = Icons.Default.Cancel,
                colorFondo = MaterialTheme.colorScheme.error,
                colorTexto = MaterialTheme.colorScheme.onError
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun FilaDetalle(
    etiqueta: String,
    valor: String
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
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )
    }
}
