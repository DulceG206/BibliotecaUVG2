package scr.Controlador;

import scr.Modelo.Computadora;
import java.util.ArrayList;
import java.util.List;

public class ControladorComputadoras {
    private List<Computadora> computadoras;

    public ControladorComputadoras() {
        this.computadoras = new ArrayList<>();
        inicializarComputadoras();
    }

    private void inicializarComputadoras() {
        // Computadoras de ejemplo
        computadoras.add(new Computadora("C1", "Sala de Computo 1", true, false));
        computadoras.add(new Computadora("C2", "Sala de Computo 1", true, false));
        computadoras.add(new Computadora("C3", "Sala de Computo 1", false, true)); // En uso
        computadoras.add(new Computadora("C4", "Sala de Computo 1", true, false));
        computadoras.add(new Computadora("C5", "Sala de Computo 1", false, false)); // En mantenimiento
        computadoras.add(new Computadora("C6", "Sala de Computo 2", true, false));
        computadoras.add(new Computadora("C7", "Sala de Computo 2", true, false));
        computadoras.add(new Computadora("C8", "Sala de Computo 2", false, true)); // En uso
    }

    public List<Computadora> obtenerComputadorasDisponibles() {
        List<Computadora> disponibles = new ArrayList<>();
        for (Computadora comp : computadoras) {
            if (comp.isDisponible() && !comp.isEnUso()) {
                disponibles.add(comp);
            }
        }
        return disponibles;
    }

    public List<Computadora> obtenerComputadorasEnUso() {
        List<Computadora> enUso = new ArrayList<>();
        for (Computadora comp : computadoras) {
            if (comp.isEnUso()) {
                enUso.add(comp);
            }
        }
        return enUso;
    }

    public List<Computadora> obtenerComputadorasMantenimiento() {
        List<Computadora> mantenimiento = new ArrayList<>();
        for (Computadora comp : computadoras) {
            if (!comp.isDisponible()) {
                mantenimiento.add(comp);
            }
        }
        return mantenimiento;
    }

    public boolean usarComputadora(String idComputadora) {
        for (Computadora comp : computadoras) {
            if (comp.getId().equals(idComputadora) && comp.isDisponible() && !comp.isEnUso()) {
                comp.setEnUso(true);
                return true;
            }
        }
        return false;
    }

    public boolean liberarComputadora(String idComputadora) {
        for (Computadora comp : computadoras) {
            if (comp.getId().equals(idComputadora) && comp.isEnUso()) {
                comp.setEnUso(false);
                return true;
            }
        }
        return false;
    }

    public List<Computadora> getComputadoras() {
        return computadoras;
    }
}
