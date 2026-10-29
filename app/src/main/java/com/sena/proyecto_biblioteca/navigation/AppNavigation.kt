package com.sena.proyecto_biblioteca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sena.proyecto_biblioteca.screen.ConfirmacionScreen
import com.sena.proyecto_biblioteca.screen.DetalleLibroScreen
import com.sena.proyecto_biblioteca.screen.InicioBibliotecaScreen
import com.sena.proyecto_biblioteca.screen.ListaLibrosScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.InicioBiblioteca.route
    ) {
        // 1. Pantalla de Bienvenida / Inicio
        composable(route = ScreenRoutes.InicioBiblioteca.route) {
            InicioBibliotecaScreen(
                onNavigateToListaLibros = {
                    navController.navigate(ScreenRoutes.ListaLibros.route)
                }
            )
        }

        // 2. Pantalla de Lista de Libros
        composable(route = ScreenRoutes.ListaLibros.route) {
            ListaLibrosScreen(
                onNavigateToDetalle = { titulo, autor, categoria, descripcion ->
                    navController.navigate(
                        ScreenRoutes.DetalleLibro.createRoute(
                            titulo = titulo,
                            autor = autor,
                            categoria = categoria,
                            descripcion = descripcion
                        )
                    )
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // 3. Pantalla de Detalle del Libro
        composable(
            route = ScreenRoutes.DetalleLibro.route,
            arguments = listOf(
                navArgument("titulo") { type = NavType.StringType },
                navArgument("autor") { type = NavType.StringType },
                navArgument("categoria") { type = NavType.StringType },
                navArgument("descripcion") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val rawTitulo = backStackEntry.arguments?.getString("titulo") ?: ""
            val rawAutor = backStackEntry.arguments?.getString("autor") ?: ""
            val rawCategoria = backStackEntry.arguments?.getString("categoria") ?: ""
            val rawDescripcion = backStackEntry.arguments?.getString("descripcion") ?: ""

            val titulo = ScreenRoutes.decodeParam(rawTitulo)
            val autor = ScreenRoutes.decodeParam(rawAutor)
            val categoria = ScreenRoutes.decodeParam(rawCategoria)
            val descripcion = ScreenRoutes.decodeParam(rawDescripcion)

            DetalleLibroScreen(
                titulo = titulo,
                autor = autor,
                categoria = categoria,
                descripcion = descripcion,
                onNavigateToConfirmacion = { bookTitle ->
                    navController.navigate(ScreenRoutes.Confirmacion.createRoute(bookTitle))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // 4. Pantalla de Confirmación de Reserva
        composable(
            route = ScreenRoutes.Confirmacion.route,
            arguments = listOf(
                navArgument("titulo") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val rawTitulo = backStackEntry.arguments?.getString("titulo") ?: ""
            val titulo = ScreenRoutes.decodeParam(rawTitulo)

            ConfirmacionScreen(
                titulo = titulo,
                onNavigateToInicio = {
                    navController.navigate(ScreenRoutes.InicioBiblioteca.route) {
                        popUpTo(ScreenRoutes.InicioBiblioteca.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
