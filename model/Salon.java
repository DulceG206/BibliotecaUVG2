package model;

public class Salon {
    private String nombre;
    private boolean disponible = true;

    public Salon(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
    public boolean isDisponible() { return disponible; }

    public void apartar() {
        disponible = false;
    }
}
