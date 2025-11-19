package model;

import java.time.LocalDate;

public class Libro {
    private String titulo;
    private boolean disponible = true;
    private LocalDate fechaDevolucion;

    public Libro(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() { return titulo; }
    public boolean isDisponible() { return disponible; }

    public void apartar() {
        disponible = false;
        fechaDevolucion = LocalDate.now().plusDays(15);
    }

    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
}
