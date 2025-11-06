package scr.Modelo;

public class Salon {
    private String id;
    private String nombre;
    private int capacidad;
    private boolean disponible;

    public Salon(String id, String nombre, int capacidad, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.disponible = disponible;
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }
    public boolean isDisponible() { return disponible; }
    
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return nombre + " (Capacidad: " + capacidad + ") - " + 
               (disponible ? " Disponible" : "❌No disponible");
    }
}