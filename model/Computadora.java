package model;

public class Computadora {
    private int numero;
    private boolean disponible = true;

    public Computadora(int numero) {
        this.numero = numero;
    }

    public int getNumero() { return numero; }
    public boolean isDisponible() { return disponible; }

    public void apartar() {
        disponible = false;
    }
}
