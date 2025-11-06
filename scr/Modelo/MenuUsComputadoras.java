package scr.Modelo;

import scr.Controlador.ControladorComputadoras;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuUsComputadoras extends JPanel {
    private ControladorComputadoras controladorComputadoras;
    private JTextArea areaComputadoras;
    private JComboBox<String> comboFiltro;

    public MenuUsComputadoras() {
        this.controladorComputadoras = new ControladorComputadoras();
        
        setLayout(new BorderLayout(10, 10));
        initComponentes();
        cargarComputadorasDisponibles();
    }

    private void initComponentes() {
        // Panel superior - Filtros
        JPanel panelFiltros = new JPanel(new FlowLayout());
        panelFiltros.setBorder(BorderFactory.createTitledBorder("Filtrar Computadoras"));

        panelFiltros.add(new JLabel("Mostrar:"));
        comboFiltro = new JComboBox<>(new String[]{"Disponibles", "En Uso", "En Mantenimiento", "Todas"});
        comboFiltro.addActionListener(e -> cargarComputadoras());
        panelFiltros.add(comboFiltro);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> cargarComputadoras());
        panelFiltros.add(btnActualizar);

        // Panel central - Lista de computadoras
        JPanel panelComputadoras = new JPanel(new BorderLayout());
        panelComputadoras.setBorder(BorderFactory.createTitledBorder("Estado de Computadoras"));

        areaComputadoras = new JTextArea(15, 50);
        areaComputadoras.setEditable(false);
        areaComputadoras.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollComputadoras = new JScrollPane(areaComputadoras);
        panelComputadoras.add(scrollComputadoras, BorderLayout.CENTER);

        // Panel inferior - Estadísticas
        JPanel panelStats = new JPanel(new GridLayout(1, 3));
        panelStats.setBorder(BorderFactory.createTitledBorder("Estadísticas"));

        JLabel lblDisponibles = new JLabel("", JLabel.CENTER);
        JLabel lblEnUso = new JLabel("", JLabel.CENTER);
        JLabel lblMantenimiento = new JLabel("", JLabel.CENTER);

        panelStats.add(lblDisponibles);
        panelStats.add(lblEnUso);
        panelStats.add(lblMantenimiento);

        // Agregar componentes al panel principal
        add(panelFiltros, BorderLayout.NORTH);
        add(panelComputadoras, BorderLayout.CENTER);
        add(panelStats, BorderLayout.SOUTH);

        // Actualizar estadísticas
        actualizarEstadisticas(lblDisponibles, lblEnUso, lblMantenimiento);
    }

    private void cargarComputadoras() {
        String filtro = (String) comboFiltro.getSelectedItem();
        List<Computadora> computadoras;

        switch (filtro) {
            case "Disponibles":
                computadoras = controladorComputadoras.obtenerComputadorasDisponibles();
                break;
            case "En Uso":
                computadoras = controladorComputadoras.obtenerComputadorasEnUso();
                break;
            case "En Mantenimiento":
                computadoras = controladorComputadoras.obtenerComputadorasMantenimiento();
                break;
            default:
                computadoras = controladorComputadoras.getComputadoras();
                break;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== COMPUTADORAS ").append(filtro.toUpperCase()).append(" ===\n\n");
        
        if (computadoras.isEmpty()) {
            sb.append("No hay computadoras que mostrar.\n");
        } else {
            for (Computadora comp : computadoras) {
                sb.append(comp.toString()).append("\n");
            }
        }

        areaComputadoras.setText(sb.toString());
    }

    private void cargarComputadorasDisponibles() {
        comboFiltro.setSelectedItem("Disponibles");
        cargarComputadoras();
    }

    private void actualizarEstadisticas(JLabel lblDisponibles, JLabel lblEnUso, JLabel lblMantenimiento) {
        List<Computadora> disponibles = controladorComputadoras.obtenerComputadorasDisponibles();
        List<Computadora> enUso = controladorComputadoras.obtenerComputadorasEnUso();
        List<Computadora> mantenimiento = controladorComputadoras.obtenerComputadorasMantenimiento();
        int total = controladorComputadoras.getComputadoras().size();

        lblDisponibles.setText("<html><center> Disponibles<br>" + disponibles.size() + "/" + total + "</center></html>");
        lblEnUso.setText("<html><center> En Uso<br>" + enUso.size() + "/" + total + "</center></html>");
        lblMantenimiento.setText("<html><center>🔧 Mantenimiento<br>" + mantenimiento.size() + "/" + total + "</center></html>");
    }
}