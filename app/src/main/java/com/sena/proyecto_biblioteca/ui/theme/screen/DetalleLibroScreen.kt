package com.sena.proyecto_biblioteca.ui.theme.screen

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sena.proyecto_biblioteca.ui.theme.composable.BarraNavegacionSuperior
import com.sena.proyecto_biblioteca.ui.theme.composable.BotonPersonalizado
import com.sena.proyecto_biblioteca.ui.theme.composable.ContenedorTarjeta
import com.sena.proyecto_biblioteca.ui.theme.section.CabeceraSeccion

@Composable
fun DetalleLibroScreen(
    titulo: String,
    autor: String,
    categoria: String,
    descripcion: String,
    onNavigateToConfirmacion: (titulo: String) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            BarraNavegacionSuperior(
                titulo = "Detalle del Libro",
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
            // Main Book Header Card
            ContenedorTarjeta(elevation = 6.dp) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.MenuBook,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(54.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = titulo,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Autor: $autor",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Text(
                            text = categoria,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Metadata Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ItemDetalleRapido(
                    icono = Icons.Default.CheckCircle,
                    etiqueta = "Estado",
                    valor = "Disponible",
                    modifier = Modifier.weight(1f)
                )
                ItemDetalleRapido(
                    icono = Icons.Default.LocationOn,
                    etiqueta = "Ubicación",
                    valor = "Estante B-04",
                    modifier = Modifier.weight(1f)
                )
                ItemDetalleRapido(
                    icono = Icons.AutoMirrored.Filled.LibraryBooks,
                    etiqueta = "Formato",
                    valor = "Físico",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Description Section
            CabeceraSeccion(
                titulo = "Sinopsis del Libro",
                subtitulo = "Resumen del contenido y trama principal"
            )

            Spacer(modifier = Modifier.height(8.dp))

            ContenedorTarjeta(elevation = 2.dp) {
                Text(
                    text = descripcion.ifBlank { "No hay descripción disponible para este libro." },
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Reserve Button
            BotonPersonalizado(
                texto = "Reservar este Libro",
                onClick = { onNavigateToConfirmacion(titulo) },
                icono = Icons.Default.Bookmark
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ItemDetalleRapido(
    icono: ImageVector,
    etiqueta: String,
    valor: String,
    modifier: Modifier = Modifier
) {
    ContenedorTarjeta(
        modifier = modifier,
        elevation = 2.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = etiqueta,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = valor,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
