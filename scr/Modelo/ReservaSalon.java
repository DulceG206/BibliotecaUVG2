package scr.Modelo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ReservaSalon {
    private Usuario usuario;
    private String idSalon;
    private LocalDateTime fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public ReservaSalon(Usuario usuario, String idSalon, LocalDateTime fecha, 
                       LocalTime horaInicio, LocalTime horaFin) {
        this.usuario = usuario;
        this.idSalon = idSalon;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    // Getters
    public Usuario getUsuario() { return usuario; }
    public String getIdSalon() { return idSalon; }
    public LocalDateTime getFecha() { return fecha; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        
        return "Salón: " + idSalon + 
               " | Fecha: " + fecha.format(dateFormatter) +
               " | Horario: " + horaInicio.format(timeFormatter) + " - " + horaFin.format(timeFormatter) +
               " | Usuario: " + usuario.getNombre();
    }
}