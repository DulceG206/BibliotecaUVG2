public class Salon {
    private int numero;
    private int capacidad;
    private boolean tieneTele;

    // CORREGIDO: Constructor inicializa variables
    public Salon(int numero, int capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.tieneTele = false; // valor por defecto
    }
    
    public void apartarSalon(String nombre) {
        // Implementación pendiente
    } 

    public void test() {
        // Método vacío - posiblemente para testing
    }
    
    // CORREGIDO: Getters para acceder a las propiedades
    public int getNumero() {
        return numero;
    }
    
    public int getCapacidad() {
        return capacidad;
    }
    
    public boolean getTieneTele() {
        return tieneTele;
    }
}