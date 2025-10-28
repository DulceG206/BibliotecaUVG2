import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibroController {

    private List<Libro> listaLibros;
    private List<Libro> librosReservados;

    public LibroController() {
        listaLibros = new ArrayList<>();
        librosReservados = new ArrayList<>();

        
        listaLibros.add(new Libro("", ""));
        listaLibros.add(new Libro("", ""));
        listaLibros.add(new Libro("", ""));
        listaLibros.add(new Libro("", ""));
        listaLibros.add(new Libro("", ""));
    }

    public List<Libro> buscarLibros(String texto) {
        return listaLibros.stream()
                .filter(libro -> libro.getNombre().toLowerCase().contains(texto.toLowerCase())
                        || libro.getAutor().toLowerCase().contains(texto.toLowerCase()))
                .collect(Collectors.toList());
    }


    public String reservarLibro(Libro libro) {
        if (libro.reservar()) {
            librosReservados.add(libro);
            return " Has reservado el libro: " + libro.getNombre() +
                   "\nFecha límite de devolución: " + libro.getFechaLimiteDevolucion();
        } else {
            return "El libro ya está reservado.";
        }
    }


    public List<Libro> getLibrosReservados() {
        return librosReservados;
    }


    public List<Libro> getTodosLosLibros() {
        return listaLibros;
    }
}
 {
    
}
