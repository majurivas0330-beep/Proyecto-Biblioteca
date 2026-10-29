package com.sena.proyecto_biblioteca.navigation

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class ScreenRoutes(val route: String) {
    object InicioBiblioteca : ScreenRoutes("inicio_biblioteca")
    object ListaLibros : ScreenRoutes("lista_libros")
    object DetalleLibro : ScreenRoutes("detalle_libro/{titulo}/{autor}/{categoria}/{descripcion}") {
        fun createRoute(
            titulo: String,
            autor: String,
            categoria: String,
            descripcion: String
        ): String {
            val tituloEnc = encodeParam(titulo)
            val autorEnc = encodeParam(autor)
            val categoriaEnc = encodeParam(categoria)
            val descripcionEnc = encodeParam(descripcion)
            return "detalle_libro/$tituloEnc/$autorEnc/$categoriaEnc/$descripcionEnc"
        }
    }
    object Confirmacion : ScreenRoutes("confirmacion/{titulo}") {
        fun createRoute(titulo: String): String {
            val tituloEnc = encodeParam(titulo)
            return "confirmacion/$tituloEnc"
        }
    }

    // --- Flujo de Mis Reservas ---
    object MisReservas : ScreenRoutes("mis_reservas")

    object DetalleReserva : ScreenRoutes("detalle_reserva/{nombreLibro}/{autor}/{fechaReserva}/{codigoReserva}/{estado}") {
        fun createRoute(
            nombreLibro: String,
            autor: String,
            fechaReserva: String,
            codigoReserva: String,
            estado: String
        ): String {
            val libroEnc = encodeParam(nombreLibro)
            val autorEnc = encodeParam(autor)
            val fechaEnc = encodeParam(fechaReserva)
            val codigoEnc = encodeParam(codigoReserva)
            val estadoEnc = encodeParam(estado)
            return "detalle_reserva/$libroEnc/$autorEnc/$fechaEnc/$codigoEnc/$estadoEnc"
        }
    }

    object RenovarReserva : ScreenRoutes("renovar_reserva/{nombreLibro}/{codigoReserva}/{fechaReserva}") {
        fun createRoute(
            nombreLibro: String,
            codigoReserva: String,
            fechaReserva: String
        ): String {
            val libroEnc = encodeParam(nombreLibro)
            val codigoEnc = encodeParam(codigoReserva)
            val fechaEnc = encodeParam(fechaReserva)
            return "renovar_reserva/$libroEnc/$codigoEnc/$fechaEnc"
        }
    }

    object RenovacionExitosa : ScreenRoutes("renovacion_exitosa/{nombreLibro}/{codigoReserva}/{fechaNueva}") {
        fun createRoute(
            nombreLibro: String,
            codigoReserva: String,
            fechaNueva: String
        ): String {
            val libroEnc = encodeParam(nombreLibro)
            val codigoEnc = encodeParam(codigoReserva)
            val fechaEnc = encodeParam(fechaNueva)
            return "renovacion_exitosa/$libroEnc/$codigoEnc/$fechaEnc"
        }
    }

    companion object {
        fun encodeParam(param: String): String {
            return try {
                URLEncoder.encode(param, StandardCharsets.UTF_8.name())
            } catch (e: Exception) {
                param
            }
        }

        fun decodeParam(param: String?): String {
            if (param == null) return ""
            return try {
                URLDecoder.decode(param, StandardCharsets.UTF_8.name())
            } catch (e: Exception) {
                param
            }
        }
    }
}
