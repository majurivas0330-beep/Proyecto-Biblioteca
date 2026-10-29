package com.sena.proyecto_biblioteca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sena.proyecto_biblioteca.ui.theme.screen.ConfirmacionScreen
import com.sena.proyecto_biblioteca.ui.theme.screen.DetalleLibroScreen
import com.sena.proyecto_biblioteca.ui.theme.screen.DetalleReservaScreen
import com.sena.proyecto_biblioteca.ui.theme.screen.InicioBibliotecaScreen
import com.sena.proyecto_biblioteca.ui.theme.screen.ListaLibrosScreen
import com.sena.proyecto_biblioteca.ui.theme.screen.MisReservasScreen
import com.sena.proyecto_biblioteca.ui.theme.screen.RenovacionExitosaScreen
import com.sena.proyecto_biblioteca.ui.theme.screen.RenovarReservaScreen

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
                },
                onNavigateToMisReservas = {
                    navController.navigate(ScreenRoutes.MisReservas.route)
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

        // ==========================================
        // FLUJO DE MIS RESERVAS
        // ==========================================

        // 5. Pantalla de Mis Reservas
        composable(route = ScreenRoutes.MisReservas.route) {
            MisReservasScreen(
                onNavigateToDetalleReserva = { nombreLibro, autor, fechaReserva, codigoReserva, estado ->
                    navController.navigate(
                        ScreenRoutes.DetalleReserva.createRoute(
                            nombreLibro = nombreLibro,
                            autor = autor,
                            fechaReserva = fechaReserva,
                            codigoReserva = codigoReserva,
                            estado = estado
                        )
                    )
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // 6. Pantalla de Detalle de Reserva
        composable(
            route = ScreenRoutes.DetalleReserva.route,
            arguments = listOf(
                navArgument("nombreLibro") { type = NavType.StringType },
                navArgument("autor") { type = NavType.StringType },
                navArgument("fechaReserva") { type = NavType.StringType },
                navArgument("codigoReserva") { type = NavType.StringType },
                navArgument("estado") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val rawLibro = backStackEntry.arguments?.getString("nombreLibro") ?: ""
            val rawAutor = backStackEntry.arguments?.getString("autor") ?: ""
            val rawFecha = backStackEntry.arguments?.getString("fechaReserva") ?: ""
            val rawCodigo = backStackEntry.arguments?.getString("codigoReserva") ?: ""
            val rawEstado = backStackEntry.arguments?.getString("estado") ?: ""

            val nombreLibro = ScreenRoutes.decodeParam(rawLibro)
            val autor = ScreenRoutes.decodeParam(rawAutor)
            val fechaReserva = ScreenRoutes.decodeParam(rawFecha)
            val codigoReserva = ScreenRoutes.decodeParam(rawCodigo)
            val estado = ScreenRoutes.decodeParam(rawEstado)

            DetalleReservaScreen(
                nombreLibro = nombreLibro,
                autor = autor,
                fechaReserva = fechaReserva,
                codigoReserva = codigoReserva,
                estado = estado,
                onNavigateToRenovar = { libro, codigo, fecha ->
                    navController.navigate(
                        ScreenRoutes.RenovarReserva.createRoute(
                            nombreLibro = libro,
                            codigoReserva = codigo,
                            fechaReserva = fecha
                        )
                    )
                },
                onCancelarReserva = {
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // 7. Pantalla de Renovar Reserva
        composable(
            route = ScreenRoutes.RenovarReserva.route,
            arguments = listOf(
                navArgument("nombreLibro") { type = NavType.StringType },
                navArgument("codigoReserva") { type = NavType.StringType },
                navArgument("fechaReserva") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val rawLibro = backStackEntry.arguments?.getString("nombreLibro") ?: ""
            val rawCodigo = backStackEntry.arguments?.getString("codigoReserva") ?: ""
            val rawFecha = backStackEntry.arguments?.getString("fechaReserva") ?: ""

            val nombreLibro = ScreenRoutes.decodeParam(rawLibro)
            val codigoReserva = ScreenRoutes.decodeParam(rawCodigo)
            val fechaReserva = ScreenRoutes.decodeParam(rawFecha)

            RenovarReservaScreen(
                nombreLibro = nombreLibro,
                codigoReserva = codigoReserva,
                fechaReserva = fechaReserva,
                onNavigateToExitosa = { libro, codigo, fechaNueva ->
                    navController.navigate(
                        ScreenRoutes.RenovacionExitosa.createRoute(
                            nombreLibro = libro,
                            codigoReserva = codigo,
                            fechaNueva = fechaNueva
                        )
                    )
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // 8. Pantalla de Renovación Exitosa
        composable(
            route = ScreenRoutes.RenovacionExitosa.route,
            arguments = listOf(
                navArgument("nombreLibro") { type = NavType.StringType },
                navArgument("codigoReserva") { type = NavType.StringType },
                navArgument("fechaNueva") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val rawLibro = backStackEntry.arguments?.getString("nombreLibro") ?: ""
            val rawCodigo = backStackEntry.arguments?.getString("codigoReserva") ?: ""
            val rawFecha = backStackEntry.arguments?.getString("fechaNueva") ?: ""

            val nombreLibro = ScreenRoutes.decodeParam(rawLibro)
            val codigoReserva = ScreenRoutes.decodeParam(rawCodigo)
            val fechaNueva = ScreenRoutes.decodeParam(rawFecha)

            RenovacionExitosaScreen(
                nombreLibro = nombreLibro,
                codigoReserva = codigoReserva,
                fechaNueva = fechaNueva,
                onVolverAMisReservas = {
                    navController.navigate(ScreenRoutes.MisReservas.route) {
                        popUpTo(ScreenRoutes.MisReservas.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
