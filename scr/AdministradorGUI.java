package scr;
import javax.swing.*;

import scr.Administrador.Administrador;

import java.awt.*;
import java.awt.event.*;

public class AdministradorGUI extends JFrame {
    private Administrador admin;

    private final JTextField nombreField = new JTextField(15);
    private final JTextField turnoField = new JTextField(10);
    private final JPasswordField contraseñaField = new JPasswordField(10);

    private final JTextField libroField = new JTextField(15);
    private final DefaultListModel<Libro> listModel = new DefaultListModel<>();
    private final JList<Libro> libroList = new JList<>(listModel);

    public AdministradorGUI() {
        super("Administración - BibliotecaUVG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 360);
        setLocationRelativeTo(null);
        initLayout();
    }

    private void initLayout() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBorder(BorderFactory.createTitledBorder("Crear/Actualizar administrador"));
        top.add(new JLabel("Nombre:"));
        top.add(nombreField);
        top.add(new JLabel("Turno:"));
        top.add(turnoField);
        top.add(new JLabel("Contraseña:"));
        top.add(contraseñaField);
        JButton crearBtn = new JButton("Crear/Actualizar");
        top.add(crearBtn);

        crearBtn.addActionListener(e -> {
            String nombre = nombreField.getText().trim();
            String turno = turnoField.getText().trim();
            String pass = new String(contraseñaField.getPassword()).trim();
            if (nombre.isEmpty() || turno.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Rellena nombre, turno y contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            admin = new Administrador(nombre, turno, pass);
            refreshLista();
            JOptionPane.showMessageDialog(this, "Administrador creado/actualizado.", "OK", JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(BorderFactory.createTitledBorder("Gestión de libros"));

        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addPanel.add(new JLabel("Título libro:"));
        addPanel.add(libroField);
        JButton addBtn = new JButton("Agregar libro");
        addPanel.add(addBtn);
        JButton removeBtn = new JButton("Eliminar seleccionado");
        addPanel.add(removeBtn);

        addBtn.addActionListener(e -> {
            if (admin == null) {
                JOptionPane.showMessageDialog(this, "Primero crea el administrador.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String titulo = libroField.getText().trim();
            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Escribe el título del libro.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Libro agregado = admin.agregaLibro(titulo);
            listModel.addElement(agregado);
            libroField.setText("");
        });

        removeBtn.addActionListener(e -> {
            if (admin == null) {
                JOptionPane.showMessageDialog(this, "Primero crea el administrador.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Libro seleccionado = libroList.getSelectedValue();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un libro para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean ok = admin.eliminarLibro(seleccionado);
            if (ok) {
                listModel.removeElement(seleccionado);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        libroList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(libroList);

        center.add(addPanel, BorderLayout.NORTH);
        center.add(scroll, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout(8, 8));
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);
    }

    private void refreshLista() {
        listModel.clear();
        if (admin != null) {
            for (Libro l : admin.getLibros()) {
                listModel.addElement(l);
            }
        }
    }
}