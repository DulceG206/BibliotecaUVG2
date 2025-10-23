package scr.Administrador;
// ...existing code...
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Administrador {
    private String nombre;
    private String turno;
    private String contraseña;
    private final List<Libro> libros = new ArrayList<>();

    // Constructor
    public Administrador(String nombre, String turno, String contraseña) {
        this.nombre = nombre;
        this.turno = turno;
        this.contraseña = contraseña;
    }

    // Getters / Setters
    public String getContraseña() {
        return contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTurno() {
        return turno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    // Agrega un libro (por título) y lo retorna
    public Libro agregaLibro(String titulo) {
        Libro libro = new Libro(titulo);
        libros.add(libro);
        return libro;
    }

    // Elimina un libro dado el objeto Libro (si existe)
    public boolean eliminarLibro(Libro libro) {
        return libros.remove(libro);
    }

    // Elimina por título (elimina el primer match)
    public boolean eliminarLibroPorTitulo(String titulo) {
        for (Libro l : new ArrayList<>(libros)) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return libros.remove(l);
            }
        }
        return false;
    }

    // Devuelve copia de la lista de libros
    public List<Libro> getLibros() {
        return Collections.unmodifiableList(new ArrayList<>(libros));
    }
}
