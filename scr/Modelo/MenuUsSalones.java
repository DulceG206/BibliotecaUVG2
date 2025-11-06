package scr.Modelo;

import scr.Controlador.ControladorSalones;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MenuUsSalones extends JPanel {
    private ControladorSalones controladorSalones;
    private Usuario usuarioActual;
    private JComboBox<String> comboSalones;
    private JSpinner spinnerFecha;
    private JComboBox<String> comboHorarios;
    private JTextArea areaReservas;
    private JButton btnReservar;

    public MenuUsSalones(Usuario usuario) {
        this.usuarioActual = usuario;
        this.controladorSalones = new ControladorSalones();
        
        setLayout(new BorderLayout(10, 10));
        initComponentes();
        cargarSalonesDisponibles();
    }

    private void initComponentes() {
        // Panel superior - Formulario de reserva
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Reservar Salón"));

        // Salón
        panelFormulario.add(new JLabel("Salón:"));
        comboSalones = new JComboBox<>();
        comboSalones.addActionListener(e -> cargarHorariosDisponibles());
        panelFormulario.add(comboSalones);

        // Fecha
        panelFormulario.add(new JLabel("Fecha:"));
        SpinnerDateModel dateModel = new SpinnerDateModel();
        spinnerFecha = new JSpinner(dateModel);
        spinnerFecha.setEditor(new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy"));
        spinnerFecha.addChangeListener(e -> cargarHorariosDisponibles());
        panelFormulario.add(spinnerFecha);

        // Horario
        panelFormulario.add(new JLabel("Horario:"));
        comboHorarios = new JComboBox<>();
        panelFormulario.add(comboHorarios);

        // Botón reservar
        panelFormulario.add(new JLabel());
        btnReservar = new JButton("Reservar Salón");
        btnReservar.addActionListener(e -> reservarSalon());
        panelFormulario.add(btnReservar);

        // Panel central - Lista de reservas del usuario
        JPanel panelReservas = new JPanel(new BorderLayout());
        panelReservas.setBorder(BorderFactory.createTitledBorder("Mis Reservas"));

        areaReservas = new JTextArea(10, 40);
        areaReservas.setEditable(false);
        JScrollPane scrollReservas = new JScrollPane(areaReservas);
        panelReservas.add(scrollReservas, BorderLayout.CENTER);

        // Botón actualizar reservas
        JButton btnActualizar = new JButton("Actualizar Reservas");
        btnActualizar.addActionListener(e -> cargarReservasUsuario());
        panelReservas.add(btnActualizar, BorderLayout.SOUTH);

        // Agregar componentes al panel principal
        add(panelFormulario, BorderLayout.NORTH);
        add(panelReservas, BorderLayout.CENTER);

        // Cargar reservas iniciales
        cargarReservasUsuario();
    }

    private void cargarSalonesDisponibles() {
        comboSalones.removeAllItems();
        List<Salon> salones = controladorSalones.obtenerSalonesDisponibles();
        
        for (Salon salon : salones) {
            comboSalones.addItem(salon.getId() + " - " + salon.getNombre());
        }
        
        if (!salones.isEmpty()) {
            cargarHorariosDisponibles();
        }
    }

    private void cargarHorariosDisponibles() {
        comboHorarios.removeAllItems();
        
        if (comboSalones.getSelectedItem() == null) return;
        
        String salonSeleccionado = comboSalones.getSelectedItem().toString().split(" - ")[0];
        LocalDate fecha = ((java.util.Date) spinnerFecha.getValue()).toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDateTime fechaCompleta = fecha.atStartOfDay();
        
        List<LocalTime[]> horarios = controladorSalones.obtenerHorariosDisponibles(salonSeleccionado, fechaCompleta);
        
        for (LocalTime[] horario : horarios) {
            comboHorarios.addItem(horario[0] + " - " + horario[1]);
        }
    }

    private void reservarSalon() {
        if (comboSalones.getSelectedItem() == null || comboHorarios.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un salón y horario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String salonSeleccionado = comboSalones.getSelectedItem().toString().split(" - ")[0];
            LocalDate fecha = ((java.util.Date) spinnerFecha.getValue()).toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            
            String[] horario = comboHorarios.getSelectedItem().toString().split(" - ");
            LocalTime horaInicio = LocalTime.parse(horario[0]);
            LocalTime horaFin = LocalTime.parse(horario[1]);
            
            LocalDateTime fechaCompleta = fecha.atStartOfDay();

            boolean exito = controladorSalones.reservarSalon(usuarioActual, salonSeleccionado, 
                                                           fechaCompleta, horaInicio, horaFin);
            
            if (exito) {
                JOptionPane.showMessageDialog(this, 
                    "Salón reservado exitosamente\n" +
                    "Salón: " + salonSeleccionado + "\n" +
                    "Fecha: " + fecha + "\n" +
                    "Horario: " + horaInicio + " - " + horaFin,
                    "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);
                cargarReservasUsuario();
                cargarHorariosDisponibles(); // Actualizar horarios disponibles
            } else {
                JOptionPane.showMessageDialog(this, 
                    "El salón ya está reservado en ese horario", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al procesar la reserva", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarReservasUsuario() {
        List<ReservaSalon> reservas = controladorSalones.obtenerReservasUsuario(usuarioActual);
        StringBuilder sb = new StringBuilder();
        
        if (reservas.isEmpty()) {
            sb.append("No tiene reservas de salones activas.\n");
        } else {
            sb.append("=== MIS RESERVAS DE SALONES ===\n\n");
            for (ReservaSalon reserva : reservas) {
                sb.append("• ").append(reserva.toString()).append("\n\n");
            }
        }
        
        areaReservas.setText(sb.toString());
    }
}