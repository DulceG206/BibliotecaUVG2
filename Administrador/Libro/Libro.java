
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Libro {
    
    private String nombre;
    private String autor;
    private boolean reservado;
    private LocalDate fechaReserva;
    private LocalDate fechaLimiteDevolucion;

    
    public Libro(String nombre, String autor) {
        this.nombre = nombre;
        this.autor = autor;
        this.reservado = false;
        this.fechaReserva = null;
        this.fechaLimiteDevolucion = null;
    }


    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isReservado() {
        return reservado;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public LocalDate getFechaLimiteDevolucion() {
        return fechaLimiteDevolucion;
    }


    public boolean reservar() {
        if (!reservado) {
            this.reservado = true;
            this.fechaReserva = LocalDate.now();
            this.fechaLimiteDevolucion = fechaReserva.plusDays(15);
            return true;
        }
        return false; 
    }

    
    public void cancelarReserva() {
        this.reservado = false;
        this.fechaReserva = null;
        this.fechaLimiteDevolucion = null;
    }

    
    public String getEstado() {
        if (reservado) {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return "Reservado hasta el " + fechaLimiteDevolucion.format(formato);
        } else {
            return "Disponible";
        }
    }

    
    @Override
    public String toString() {
        String estado = reservado ? "Reservado" : "Disponible";
        return nombre + " - " + autor + " (" + estado + ")";
    }
}
