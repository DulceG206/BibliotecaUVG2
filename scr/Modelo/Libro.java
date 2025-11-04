package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Libro implements Serializable {

    private String titulo;
    private String autor;
    private boolean disponible;
    private boolean apartado;
    private LocalDate fechaReserva;
    private LocalDate fechaLimiteDevolucion;
    private String usuarioQueAparto;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
        this.apartado = false;
        this.fechaReserva = null;
        this.fechaLimiteDevolucion = null;
        this.usuarioQueAparto = null;
    }

    public boolean reservar(String usuario) {
        if (disponible && !apartado) {
            this.apartado = true;
            this.disponible = false;
            this.usuarioQueAparto = usuario;
            this.fechaReserva = LocalDate.now();
            this.fechaLimiteDevolucion = fechaReserva.plusDays(8);
            return true;
        }
        return false;
    }

    public void cancelarReserva() {
        this.apartado = false;
        this.disponible = true;
        this.fechaReserva = null;
        this.fechaLimiteDevolucion = null;
        this.usuarioQueAparto = null;
    }

    public String getEstado() {
        if (disponible && !apartado) {
            return "Disponible";
        } else if (apartado) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return "Apartado por " + usuarioQueAparto + 
                   " (devolver antes del " + fechaLimiteDevolucion.format(fmt) + ")";
        } else {
            return "No disponible";
        }
    }

    // Getters y Setters
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isDisponible() { return disponible; }
    public boolean isApartado() { return apartado; }
    public LocalDate getFechaLimiteDevolucion() { return fechaLimiteDevolucion; }
    public String getUsuarioQueAparto() { return usuarioQueAparto; }

    @Override
    public String toString() {
        return titulo + " - " + autor + " | " + getEstado();
    }
}
