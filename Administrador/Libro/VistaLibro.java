import controlador.LibroController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class VistaLibro extends JFrame {

    private LibroController controller;
    private JTextField txtBuscar;
    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;
    private JButton btnBuscar, btnVerReservas;

    public VistaLibro(LibroController controller) {
        this.controller = controller;
        setTitle("Biblioteca Virtual");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        cargarTabla(controller.getTodosLosLibros());
    }

    private void initComponents() {
        setLayout(new BorderLayout());

    
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());

        JLabel lblBuscar = new JLabel("Buscar libro o autor:");
        txtBuscar = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        btnVerReservas = new JButton("Ver mis reservas");

        panelSuperior.add(lblBuscar);
        panelSuperior.add(txtBuscar);
        panelSuperior.add(btnBuscar);
        panelSuperior.add(btnVerReservas);

        add(panelSuperior, BorderLayout.NORTH);

        
        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Autor", "Estado", "Acción"}, 0);
        tablaLibros = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaLibros);
        add(scroll, BorderLayout.CENTER);


        btnBuscar.addActionListener(e -> buscar());
        btnVerReservas.addActionListener(e -> mostrarReservas());

        
        tablaLibros.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = tablaLibros.getSelectedRow();
                    if (fila >= 0) {
                        String nombre = (String) modeloTabla.getValueAt(fila, 0);
                        Libro libro = controller.getTodosLosLibros().stream()
                                .filter(l -> l.getNombre().equals(nombre))
                                .findFirst().orElse(null);

                        if (libro != null) {
                            String mensaje = controller.reservarLibro(libro);
                            JOptionPane.showMessageDialog(VistaLibro.this, mensaje);
                            buscar(); 
                        }
                    }
                }
            }
        });
    }

    private void cargarTabla(java.util.List<Libro> libros) {
        modeloTabla.setRowCount(0);
        for (Libro l : libros) {
            modeloTabla.addRow(new Object[]{
                    l.getNombre(),
                    l.getAutor(),
                    l.getEstado(),
                    l.isReservado() ? "No disponible" : "Reservar"
            });
        }
    }

    private void buscar() {
        String texto = txtBuscar.getText();
        if (texto.isEmpty()) {
            cargarTabla(controller.getTodosLosLibros());
        } else {
            cargarTabla(controller.buscarLibros(texto));
        }
    }

    private void mostrarReservas() {
        java.util.List<Libro> reservados = controller.getLibrosReservados();
        if (reservados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tienes libros reservados aún.");
        } else {
            StringBuilder sb = new StringBuilder("📚 Libros reservados:\n\n");
            for (Libro l : reservados) {
                sb.append("• ").append(l.getNombre())
                        .append(" - Devolver antes del: ")
                        .append(l.getFechaLimiteDevolucion())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        }
    }
}
