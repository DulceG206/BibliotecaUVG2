
package scr.Modelo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// CORREGIDO: Clase Libro necesaria para compilar
class Libro {
    private String titulo;
    
    public Libro(String titulo) {
        this.titulo = titulo;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    @Override
    public String toString() {
        return titulo;
    }
}

public class Administrador {
    private String nombre;
    private String turno;
    private String contraseña;
    private final List<Libro> libros = new ArrayList<>();

    public Administrador(String nombre, String turno, String contraseña) {
        this.nombre = nombre;
        this.turno = turno;
        this.contraseña = contraseña;
    }

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

    public Libro agregaLibro(String titulo) {
        Libro libro = new Libro(titulo);
        libros.add(libro);
        return libro;
    }

    public boolean eliminarLibro(Libro libro) {
        return libros.remove(libro);
    }

    public boolean eliminarLibroPorTitulo(String titulo) {
        for (Libro l : new ArrayList<>(libros)) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return libros.remove(l);
            }
        }
        return false;
    }

    public List<Libro> getLibros() {
        return Collections.unmodifiableList(new ArrayList<>(libros));
    }
}