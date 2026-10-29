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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sena.proyecto_biblioteca.composable.BarraNavegacionSuperior
import com.sena.proyecto_biblioteca.composable.BotonPersonalizado
import com.sena.proyecto_biblioteca.composable.ContenedorTarjeta
import com.sena.proyecto_biblioteca.section.CabeceraSeccion

@Composable
fun InicioBibliotecaScreen(
    onNavigateToListaLibros: () -> Unit
) {
    Scaffold(
        topBar = {
            BarraNavegacionSuperior(titulo = "Biblioteca SENA")
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
            Spacer(modifier = Modifier.height(12.dp))

            // Hero Graphic Header
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LocalLibrary,
                    contentDescription = "Biblioteca Logo",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "¡Bienvenido a Proyecto Biblioteca!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tu portal digital para explorar, descubrir y reservar tus libros favoritos en un solo lugar.",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            CabeceraSeccion(
                titulo = "Servicios Destacados",
                subtitulo = "Aprovecha los beneficios de nuestro catálogo digital"
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Feature Cards Row
            ItemCaracteristica(
                icono = Icons.Default.Book,
                titulo = "Catálogo Diverso",
                descripcion = "Acceso a obras clásicas, literatura contemporánea y textos técnicos."
            )

            Spacer(modifier = Modifier.height(12.dp))

            ItemCaracteristica(
                icono = Icons.Default.Bookmark,
                titulo = "Reserva en Segundos",
                descripcion = "Asegura tus ejemplares antes de visitar la biblioteca presencial."
            )

            Spacer(modifier = Modifier.height(12.dp))

            ItemCaracteristica(
                icono = Icons.Default.AutoAwesome,
                titulo = "Búsqueda Rápida",
                descripcion = "Filtra por título, autor o categoría sin complicaciones."
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Main Action Button
            BotonPersonalizado(
                texto = "Explorar Catálogo de Libros",
                onClick = onNavigateToListaLibros,
                icono = Icons.AutoMirrored.Filled.ArrowForward
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ItemCaracteristica(
    icono: ImageVector,
    titulo: String,
    descripcion: String
) {
    ContenedorTarjeta(elevation = 2.dp) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icono,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.size(26.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = descripcion,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
