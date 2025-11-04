package vista;

import controlador.ControladorLibro;
import modelo.Libro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VistaLibros extends JFrame {

    private ControladorLibro controlador;
    private JTextField txtBuscar;
    private JTextField txtUsuario;
    private JTextArea txtResultado;
    private JButton btnBuscar;
    private JButton btnReservar;
    private JButton btnVerMisLibros;

    public VistaLibros(ControladorLibro controlador) {
        this.controlador = controlador;
        initComponents();
    }

    private void initComponents() {
        setTitle("📚 Sistema de Biblioteca - MVC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JPanel panelSuperior = new JPanel(new GridLayout(3,2,5,5));
        panelSuperior.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panelSuperior.add(txtUsuario);

        panelSuperior.add(new JLabel("Buscar libro por título:"));
        txtBuscar = new JTextField();
        panelSuperior.add(txtBuscar);

        btnBuscar = new JButton("Buscar");
        btnReservar = new JButton("Reservar");
        btnVerMisLibros = new JButton("Ver mis libros");
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnBuscar);
        panelBotones.add(btnReservar);
        panelBotones.add(btnVerMisLibros);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(txtResultado);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // Eventos
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarLibro();
            }
        });

        btnReservar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reservarLibro();
            }
        });

        btnVerMisLibros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verLibrosApartados();
            }
        });
    }

    private void buscarLibro() {
        String titulo = txtBuscar.getText().trim();
        Libro libro = controlador.buscarLibro(titulo);
        if (libro != null) {
            txtResultado.setText("Resultado:\n" + libro.toString());
        } else {
            txtResultado.setText("No se encontró el libro con ese título.");
        }
    }

    private void reservarLibro() {
        String titulo = txtBuscar.getText().trim();
        String usuario = txtUsuario.getText().trim();
        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes ingresar tu nombre de usuario.");
            return;
        }
        boolean exito = controlador.reservarLibro(titulo, usuario);
        if (exito) {
            Libro libro = controlador.buscarLibro(titulo);
            txtResultado.setText("✅ Reserva realizada correctamente.\n\n" +
                                 libro.getEstado());
        } else {
            txtResultado.setText("❌ No se pudo reservar. Puede que ya esté apartado.");
        }
    }

    private void verLibrosApartados() {
        String usuario = txtUsuario.getText().trim();
        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes ingresar tu nombre de usuario.");
            return;
        }
        List<Libro> apartados = controlador.obtenerLibrosApartadosPor(usuario);
        if (apartados.isEmpty()) {
            txtResultado.setText("No tienes libros apartados actualmente.");
        } else {
            StringBuilder sb = new StringBuilder("📘 Libros apartados por " + usuario + ":\n\n");
            for (Libro l : apartados) {
                sb.append(l.toString()).append("\n");
            }
            txtResultado.setText(sb.toString());
        }
    }
}

