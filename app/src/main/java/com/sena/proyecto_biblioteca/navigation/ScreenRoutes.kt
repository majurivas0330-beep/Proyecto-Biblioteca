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
