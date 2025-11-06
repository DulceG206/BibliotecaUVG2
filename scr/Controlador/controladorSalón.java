package scr.Controlador;

import scr.Modelo.Salon;
import scr.Modelo.ReservaSalon;
import scr.Modelo.Usuario;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ControladorSalones {
    private List<Salon> salones;
    private List<ReservaSalon> reservas;

    public ControladorSalones() {
        this.salones = new ArrayList<>();
        this.reservas = new ArrayList<>();
        inicializarSalones();
    }

    private void inicializarSalones() {
        // Salones de ejemplo
        salones.add(new Salon("S1", "Salón de Estudio 1", 10, true));
        salones.add(new Salon("S2", "Salón de Estudio 2", 8, true));
        salones.add(new Salon("S3", "Salón de Reuniones", 15, true));
        salones.add(new Salon("S4", "Sala de Conferencias", 20, false)); // En mantenimiento
        salones.add(new Salon("S5", "Salón Grupal", 12, true));
    }

    public List<Salon> obtenerSalonesDisponibles() {
        List<Salon> disponibles = new ArrayList<>();
        for (Salon salon : salones) {
            if (salon.isDisponible()) {
                disponibles.add(salon);
            }
        }
        return disponibles;
    }

    public List<LocalTime[]> obtenerHorariosDisponibles(String idSalon, LocalDateTime fecha) {
        List<LocalTime[]> horariosDisponibles = new ArrayList<>();
        
        // Horarios predefinidos
        LocalTime[] horario1 = {LocalTime.of(8, 0), LocalTime.of(10, 0)};
        LocalTime[] horario2 = {LocalTime.of(10, 0), LocalTime.of(12, 0)};
        LocalTime[] horario3 = {LocalTime.of(12, 0), LocalTime.of(14, 0)};
        LocalTime[] horario4 = {LocalTime.of(14, 0), LocalTime.of(16, 0)};
        LocalTime[] horario5 = {LocalTime.of(16, 0), LocalTime.of(18, 0)};
        
        // Verificar qué horarios están disponibles
        for (LocalTime[] horario : new LocalTime[][]{horario1, horario2, horario3, horario4, horario5}) {
            if (!estaReservado(idSalon, fecha, horario[0], horario[1])) {
                horariosDisponibles.add(horario);
            }
        }
        
        return horariosDisponibles;
    }

    private boolean estaReservado(String idSalon, LocalDateTime fecha, LocalTime inicio, LocalTime fin) {
        for (ReservaSalon reserva : reservas) {
            if (reserva.getIdSalon().equals(idSalon) &&
                reserva.getFecha().toLocalDate().equals(fecha.toLocalDate()) &&
                reserva.getHoraInicio().equals(inicio) &&
                reserva.getHoraFin().equals(fin)) {
                return true;
            }
        }
        return false;
    }

    public boolean reservarSalon(Usuario usuario, String idSalon, LocalDateTime fecha, 
                               LocalTime horaInicio, LocalTime horaFin) {
        if (estaReservado(idSalon, fecha, horaInicio, horaFin)) {
            return false;
        }
        
        ReservaSalon reserva = new ReservaSalon(usuario, idSalon, fecha, horaInicio, horaFin);
        reservas.add(reserva);
        return true;
    }

    public List<ReservaSalon> obtenerReservasUsuario(Usuario usuario) {
        List<ReservaSalon> reservasUsuario = new ArrayList<>();
        for (ReservaSalon reserva : reservas) {
            if (reserva.getUsuario().equals(usuario)) {
                reservasUsuario.add(reserva);
            }
        }
        return reservasUsuario;
    }

    public List<Salon> getSalones() {
        return salones;
    }
}