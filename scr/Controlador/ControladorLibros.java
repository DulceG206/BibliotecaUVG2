package scr.Controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import scr.Modelo.Libro;
import scr.Modelo.Usuario;

/**
 * ControladorLibros
 * ---
 * Controla las operaciones relacionadas con los libros:
 * - Apartar libros
 * - Consultar libros apartados por un usuario
 * - Buscar libros por título
 * - Filtrar libros por diversos criterios
 */
public class ControladorLibros {

    /**
     * Aparta un libro para el usuario, si aún está disponible.
     *
     * @param libro Libro a apartar
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
     * @param lista Lista de libros donde buscar
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
     * @param libro Libro a devolver
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
        return libro.getFechaLimiteDevolucion();
    }

    /**
     * Filtra libros por título (búsqueda parcial)
     * @param lista Lista de libros a filtrar
     * @param criterio Texto a buscar en el título
     * @return Lista de libros que coinciden con el criterio
     */
    public List<Libro> filtrarLibrosPorTitulo(List<Libro> lista, String criterio) {
        if (lista == null || criterio == null || criterio.trim().isEmpty()) {
            return new ArrayList<>(lista);
        }
        
        List<Libro> resultados = new ArrayList<>();
        String criterioLower = criterio.toLowerCase().trim();
        
        for (Libro libro : lista) {
            if (libro.getTitulo().toLowerCase().contains(criterioLower)) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    /**
     * Filtra libros por autor
     * @param lista Lista de libros a filtrar
     * @param autor Autor a buscar
     * @return Lista de libros del autor especificado
     */
    public List<Libro> filtrarLibrosPorAutor(List<Libro> lista, String autor) {
        if (lista == null || autor == null || autor.trim().isEmpty()) {
            return new ArrayList<>(lista);
        }
        
        List<Libro> resultados = new ArrayList<>();
        String autorLower = autor.toLowerCase().trim();
        
        for (Libro libro : lista) {
            if (libro.getAutor().toLowerCase().contains(autorLower)) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    /**
     * Filtra libros por disponibilidad
     * @param lista Lista de libros a filtrar
     * @param disponibles true para disponibles, false para no disponibles
     * @return Lista de libros filtrados por disponibilidad
     */
    public List<Libro> filtrarLibrosPorDisponibilidad(List<Libro> lista, boolean disponibles) {
        if (lista == null) {
            return new ArrayList<>();
        }
        
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : lista) {
            if (libro.isDisponible() == disponibles) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    /**
     * Filtra libros múltiples criterios
     * @param lista Lista de libros a filtrar
     * @param titulo Criterio para título (puede ser null)
     * @param autor Criterio para autor (puede ser null)
     * @param disponibles Estado de disponibilidad (puede ser null)
     * @return Lista de libros que cumplen con todos los criterios especificados
     */
    public List<Libro> filtrarLibros(List<Libro> lista, String titulo, String autor, Boolean disponibles) {
        if (lista == null) {
            return new ArrayList<>();
        }
        
        List<Libro> resultados = new ArrayList<>(lista);
        
        // Aplicar filtro por título si se especificó
        if (titulo != null && !titulo.trim().isEmpty()) {
            resultados = filtrarLibrosPorTitulo(resultados, titulo);
        }
        
        // Aplicar filtro por autor si se especificó
        if (autor != null && !autor.trim().isEmpty()) {
            resultados = filtrarLibrosPorAutor(resultados, autor);
        }
        
        // Aplicar filtro por disponibilidad si se especificó
        if (disponibles != null) {
            resultados = filtrarLibrosPorDisponibilidad(resultados, disponibles);
        }
        
        return resultados;
    }
}