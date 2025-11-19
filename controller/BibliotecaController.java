package controller;

import model.*;

public class BibliotecaController {

    public Libro[] obtenerLibros() {
        return new Libro[]{
            new Libro("El Principito"),
            new Libro("Cien años de soledad"),
            new Libro("Don Quijote")
        };
    }

    public Salon[] obtenerSalones() {
        return new Salon[]{
            new Salon("Sala A"),
            new Salon("Sala B")
        };
    }

    public Computadora[] obtenerComputadoras() {
        return new Computadora[]{
            new Computadora(1),
            new Computadora(2),
            new Computadora(3)
        };
    }
}

