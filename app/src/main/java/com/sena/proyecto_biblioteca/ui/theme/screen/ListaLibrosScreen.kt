package com.sena.proyecto_biblioteca.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sena.proyecto_biblioteca.ui.theme.composable.BarraNavegacionSuperior
import com.sena.proyecto_biblioteca.ui.theme.data.LibrosRepository
import com.sena.proyecto_biblioteca.ui.theme.section.CabeceraSeccion
import com.sena.proyecto_biblioteca.ui.theme.section.TarjetaLibroItem

@Composable
fun ListaLibrosScreen(
    onNavigateToDetalle: (titulo: String, autor: String, categoria: String, descripcion: String) -> Unit,
    onBackClick: () -> Unit
) {
    var busqueda by remember { mutableStateOf("") }
    var categoriaSeleccionada by remember { mutableStateOf("Todas") }

    val todosLosLibros = remember { LibrosRepository.obtenerLibros() }
    val categorias = remember {
        listOf("Todas", "Realismo Mágico", "Clásicos", "Infantil / Filosofía", "Ciencia Ficción", "Novela", "Misterio")
    }

    val librosFiltrados = todosLosLibros.filter { libro ->
        val coincideBusqueda = libro.titulo.contains(busqueda, ignoreCase = true) ||
                libro.autor.contains(busqueda, ignoreCase = true)
        val coincideCategoria = categoriaSeleccionada == "Todas" || libro.categoria == categoriaSeleccionada
        coincideBusqueda && coincideCategoria
    }

    Scaffold(
        topBar = {
            BarraNavegacionSuperior(
                titulo = "Catálogo de Libros",
                mostrarBotonAtras = true,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Search Bar
            OutlinedTextField(
                value = busqueda,
                onValueChange = { busqueda = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar por título o autor...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                },
                trailingIcon = {
                    if (busqueda.isNotEmpty()) {
                        IconButton(onClick = { busqueda = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Limpiar"
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(16.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Category Filter Chips
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categorias) { cat ->
                    FilterChip(
                        selected = (cat == categoriaSeleccionada),
                        onClick = { categoriaSeleccionada = cat },
                        label = { Text(cat) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            CabeceraSeccion(
                titulo = "Libros Disponibles",
                subtitulo = "Se encontraron ${librosFiltrados.size} resultado(s)"
            )

            // Book Items List
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = librosFiltrados,
                    key = { it.id }
                ) { libro ->
                    TarjetaLibroItem(
                        libro = libro,
                        onClickDetalle = {
                            onNavigateToDetalle(
                                libro.titulo,
                                libro.autor,
                                libro.categoria,
                                libro.descripcion
                            )
                        }
                    )
                }
            }
        }
    }
}
