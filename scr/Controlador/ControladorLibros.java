package scr.Controlador;

import java.time.LocalDate;
import java.util.List;
import scr.Modelo.Libro;
import scr.Modelo.Usuario;

/**
 * ControladorLibros
 * ----------------------------------------------------
 * Controla las operaciones relacionadas con los libros:
 *  - Apartar libros
 *  - Consultar libros apartados por un usuario
 *  - Buscar libros por título
 */
public class ControladorLibros {

    /**
     * Aparta un libro para el usuario, si aún está disponible.
     * 
     * @param libro   Libro a apartar
     * @param usuario Usuario que realiza el apartado
     * @return true si se pudo apartar, false si ya estaba apartado
     */
    public boolean apartarLibro(Libro libro, Usuario usuario) {
        if (libro == null) return false;
        if (libro.isApartado()) {
            return false; // El libro ya fue apartado por alguien más
        }

        libro.apartar(usuario);
        usuario.agregarLibroApartado(libro);
        return true;
    }

    /**
     * Devuelve una lista con los libros apartados por el usuario.
     * 
     * @param usuario Usuario que consulta sus libros
     * @return Lista de libros apartados
     */
    public List<Libro> obtenerLibrosApartados(Usuario usuario) {
        if (usuario == null) return List.of();
        return usuario.getLibrosApartados();
    }

    /**
     * Busca un libro en una lista por su título.
     * 
     * @param lista  Lista de libros donde buscar
     * @param titulo Título del libro
     * @return El libro encontrado o null si no existe
     */
    public Libro buscarLibroPorTitulo(List<Libro> lista, String titulo) {
        if (lista == null || titulo == null) return null;

        for (Libro libro : lista) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    /**
     * Marca un libro como devuelto y lo libera para otros usuarios.
     * 
     * @param libro   Libro a devolver
     * @param usuario Usuario que lo tenía apartado
     */
    public void devolverLibro(Libro libro, Usuario usuario) {
        if (libro == null || usuario == null) return;

        libro.devolver();
        usuario.eliminarLibroApartado(libro);
    }

    /**
     * Calcula la fecha límite de devolución del libro (8 días después del apartado).
     * 
     * @param libro Libro del cual calcular la fecha
     * @return Fecha límite
     */
    public LocalDate obtenerFechaLimite(Libro libro) {
        if (libro == null) return null;
        return libro.getFechaLimite();
    }
}

