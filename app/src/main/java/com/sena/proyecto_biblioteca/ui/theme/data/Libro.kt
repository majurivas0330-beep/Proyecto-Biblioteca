package com.sena.proyecto_biblioteca.ui.theme.data

data class Libro(
    val id: String,
    val titulo: String,
    val autor: String,
    val categoria: String,
    val descripcion: String,
    val paginas: Int = 300,
    val anio: Int = 2020,
    val disponible: Boolean = true
)

object LibrosRepository {
    fun obtenerLibros(): List<com.sena.proyecto_biblioteca.ui.theme.data.Libro> = listOf(
        _root_ide_package_.com.sena.proyecto_biblioteca.ui.theme.data.Libro(
            id = "1",
            titulo = "Cien Años de Soledad",
            autor = "Gabriel García Márquez",
            categoria = "Realismo Mágico",
            descripcion = "La monumental obra narra la historia de la familia Buendía a lo largo de siete generaciones en el pueblo ficticio de Macondo, explorando el amor, la soledad y la historia de Colombia.",
            paginas = 471,
            anio = 1967
        ),
        _root_ide_package_.com.sena.proyecto_biblioteca.ui.theme.data.Libro(
            id = "2",
            titulo = "Don Quijote de la Mancha",
            autor = "Miguel de Cervantes",
            categoria = "Clásicos",
            descripcion = "Las aventuras del hidalgo Alonso Quijano, quien enloquece tras leer libros de caballerías y decide convertirse en caballero andante acompañado por su fiel escudero Sancho Panza.",
            paginas = 863,
            anio = 1605
        ),
        _root_ide_package_.com.sena.proyecto_biblioteca.ui.theme.data.Libro(
            id = "3",
            titulo = "El Principito",
            autor = "Antoine de Saint-Exupéry",
            categoria = "Infantil / Filosofía",
            descripcion = "Un joven príncipe que viaja por el espacio y visita diversos planetas, descubriendo la naturaleza humana, la amistad, la responsabilidad y el sentido profundo de la vida.",
            paginas = 96,
            anio = 1943
        ),
        _root_ide_package_.com.sena.proyecto_biblioteca.ui.theme.data.Libro(
            id = "4",
            titulo = "1984",
            autor = "George Orwell",
            categoria = "Ciencia Ficción",
            descripcion = "Una inquietante novela distópica que describe una sociedad bajo la permanente vigilancia del 'Gran Hermano', donde el libre pensamiento está estrictamente prohibido.",
            paginas = 328,
            anio = 1949
        ),
        _root_ide_package_.com.sena.proyecto_biblioteca.ui.theme.data.Libro(
            id = "5",
            titulo = "Rayuela",
            autor = "Julio Cortázar",
            categoria = "Novela",
            descripcion = "Una innovadora contranovela narrada con múltiples posibilidades de lectura, que relata el amor entre Horacio Oliveira y La Maga en las calles de París y Buenos Aires.",
            paginas = 600,
            anio = 1963
        ),
        _root_ide_package_.com.sena.proyecto_biblioteca.ui.theme.data.Libro(
            id = "6",
            titulo = "La Sombra del Viento",
            autor = "Carlos Ruiz Zafón",
            categoria = "Misterio",
            descripcion = "En la Barcelona de mediados del siglo XX, un joven descubre un libro maldito en el Cementerio de los Libros Olvidados, desencadenando un oscuro secreto y un enigma fascinante.",
            paginas = 565,
            anio = 2001
        )
    )
}
