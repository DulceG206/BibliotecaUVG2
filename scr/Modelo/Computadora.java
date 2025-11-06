package scr.Modelo;

public class Computadora {
    private String id;
    private String ubicacion;
    private boolean disponible;
    private boolean enUso;

    public Computadora(String id, String ubicacion, boolean disponible, boolean enUso) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.disponible = disponible;
        this.enUso = enUso;
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getUbicacion() { return ubicacion; }
    public boolean isDisponible() { return disponible; }
    public boolean isEnUso() { return enUso; }
    
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public void setEnUso(boolean enUso) { this.enUso = enUso; }

    @Override
    public String toString() {
        String estado;
        if (!disponible) {
            estado = "En mantenimiento";
        } else if (enUso) {
            estado = " En uso";
        } else {
            estado = " Disponible";
        }
        
        return "PC " + id + " | " + ubicacion + " | " + estado;
    }
}