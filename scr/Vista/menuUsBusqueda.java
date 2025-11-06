package scr.Modelo;

import javax.swing.*;
import scr.Controlador.ControladorLibros;
import scr.Controlador.controladorBusqueda;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class MenuUsBusqueda extends JPanel {

    // VARIABLES---
    JPanel panelBarraBusqueda = new JPanel();
    JTextField textFieldBuscador = new JTextField();
    JButton buttonBuscar = new JButton("Buscar");
    JButton buttonFiltrar = new JButton("Filtrar");
    JButton buttonFinal = new JButton("Botón al Final");
    
    // Nuevas variables para filtros
    private JComboBox<String> comboFiltros;
    private JTextField textFieldFiltro;
    private JButton buttonAplicarFiltro;
    private JButton buttonLimpiarFiltro;
    private List<Libro> listaLibrosCompleta;
    private List<Libro> listaLibrosFiltrada;
    private JPanel panelFiltros;

    public MenuUsBusqueda() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // layout de lista
        
        // Inicializar listas
        listaLibrosCompleta = new ArrayList<>();
        listaLibrosFiltrada = new ArrayList<>();
        
        initComponentesBusqueda();
        initComponentesFiltro();
        initListeners();
    }

    private void initComponentesBusqueda() {
        // panel barra de busqueda <<<<<
        textFieldBuscador.setPreferredSize(new Dimension(400, 40));
        buttonBuscar.setPreferredSize(new Dimension(90, 40));
        buttonFiltrar.setPreferredSize(new Dimension(90, 40));
        buttonFinal.setPreferredSize(new Dimension(90, 40));

        buttonBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
        buttonFiltrar.setFont(new Font("Arial", Font.PLAIN, 16));
        buttonFinal.setFont(new Font("Arial", Font.PLAIN, 16));

        // ADD BUSQUEDA---
        // add barra busqueda <<<<<
        panelBarraBusqueda.add(textFieldBuscador);
        panelBarraBusqueda.add(buttonBuscar);
        panelBarraBusqueda.add(Box.createHorizontalStrut(100));
        panelBarraBusqueda.add(buttonFiltrar);
        this.add(panelBarraBusqueda);
    }

    private void initComponentesFiltro() {
        // Panel de filtros (inicialmente oculto)
        panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros Avanzados"));
        panelFiltros.setVisible(false); // Inicialmente oculto
        
        // ComboBox para tipos de filtro
        String[] opcionesFiltro = {"Todos", "Por Título", "Por Autor", "Solo Disponibles", "Solo No Disponibles"};
        comboFiltros = new JComboBox<>(opcionesFiltro);
        comboFiltros.setPreferredSize(new Dimension(150, 30));
        
        // Campo de texto para filtro
        textFieldFiltro = new JTextField(20);
        textFieldFiltro.setPreferredSize(new Dimension(200, 30));
        textFieldFiltro.setToolTipText("Ingrese texto para filtrar");
        
        // Botones
        buttonAplicarFiltro = new JButton("Aplicar");
        buttonAplicarFiltro.setPreferredSize(new Dimension(80, 30));
        
        buttonLimpiarFiltro = new JButton("Limpiar");
        buttonLimpiarFiltro.setPreferredSize(new Dimension(80, 30));
        
        // Agregar componentes al panel
        panelFiltros.add(new JLabel("Filtrar por:"));
        panelFiltros.add(comboFiltros);
        panelFiltros.add(textFieldFiltro);
        panelFiltros.add(buttonAplicarFiltro);
        panelFiltros.add(buttonLimpiarFiltro);
        
        this.add(panelFiltros);
    }

    private void initListeners() {
        // Listener para mostrar/ocultar panel de filtros
        buttonFiltrar.addActionListener(e -> {
            panelFiltros.setVisible(!panelFiltros.isVisible());
            revalidate();
            repaint();
        });

        // Listener para aplicar filtros
        buttonAplicarFiltro.addActionListener(e -> aplicarFiltros());

        // Listener para limpiar filtros
        buttonLimpiarFiltro.addActionListener(e -> limpiarFiltros());

        // Mostrar/ocultar campo de texto según selección
        comboFiltros.addActionListener(e -> {
            String seleccion = (String) comboFiltros.getSelectedItem();
            boolean mostrarCampo = seleccion.equals("Por Título") || seleccion.equals("Por Autor");
            textFieldFiltro.setVisible(mostrarCampo);
            revalidate();
            repaint();
        });

        // Listener para búsqueda rápida
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLibros();
            }
        });
    }

    // Método para aplicar filtros
    private void aplicarFiltros() {
        String tipoFiltro = (String) comboFiltros.getSelectedItem();
        String criterio = textFieldFiltro.getText().trim();
        
        ControladorLibros controlador = new ControladorLibros();
        
        switch (tipoFiltro) {
            case "Por Título":
                listaLibrosFiltrada = controlador.filtrarLibrosPorTitulo(listaLibrosCompleta, criterio);
                break;
            case "Por Autor":
                listaLibrosFiltrada = controlador.filtrarLibrosPorAutor(listaLibrosCompleta, criterio);
                break;
            case "Solo Disponibles":
                listaLibrosFiltrada = controlador.filtrarLibrosPorDisponibilidad(listaLibrosCompleta, true);
                break;
            case "Solo No Disponibles":
                listaLibrosFiltrada = controlador.filtrarLibrosPorDisponibilidad(listaLibrosCompleta, false);
                break;
            case "Todos":
            default:
                listaLibrosFiltrada = new ArrayList<>(listaLibrosCompleta);
                break;
        }
        
        actualizarVistaLibros();
        mostrarResultadosFiltro();
    }

    // Método para limpiar filtros
    private void limpiarFiltros() {
        comboFiltros.setSelectedIndex(0);
        textFieldFiltro.setText("");
        listaLibrosFiltrada = new ArrayList<>(listaLibrosCompleta);
        actualizarVistaLibros();
        JOptionPane.showMessageDialog(this, "Filtros limpiados", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para búsqueda rápida
    private void buscarLibros() {
        String busqueda = textFieldBuscador.getText().trim();
        if (!busqueda.isEmpty()) {
            ControladorLibros controlador = new ControladorLibros();
            listaLibrosFiltrada = controlador.filtrarLibrosPorTitulo(listaLibrosCompleta, busqueda);
            actualizarVistaLibros();
            
            if (listaLibrosFiltrada.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No se encontraron libros con: '" + busqueda + "'", 
                    "Búsqueda sin resultados", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Se encontraron " + listaLibrosFiltrada.size() + " libros con: '" + busqueda + "'", 
                    "Resultados de búsqueda", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Si no hay búsqueda, mostrar todos los libros
            listaLibrosFiltrada = new ArrayList<>(listaLibrosCompleta);
            actualizarVistaLibros();
        }
    }

    // Método para actualizar la vista de libros
    private void actualizarVistaLibros() {
        // Limpiar libros actuales
        Component[] components = this.getComponents();
        List<Component> toRemove = new ArrayList<>();
        
        for (Component comp : components) {
            if (comp instanceof LibrosLista) {
                toRemove.add(comp);
            }
        }
        for (Component comp : toRemove) {
            remove(comp);
        }
        
        // Agregar libros filtrados
        List<Libro> librosAMostrar = listaLibrosFiltrada.isEmpty() ? listaLibrosCompleta : listaLibrosFiltrada;
        
        for (Libro libro : librosAMostrar) {
            // Usar la clase LibrosLista existente
            this.add(new LibrosLista("default.png", libro.toString()));
            this.add(Box.createVerticalStrut(20));
        }
        
        this.add(Box.createVerticalStrut(20));
        this.add(buttonFinal);
        
        revalidate();
        repaint();
    }

    // Método para mostrar información del filtro aplicado
    private void mostrarResultadosFiltro() {
        String tipoFiltro = (String) comboFiltros.getSelectedItem();
        if (!tipoFiltro.equals("Todos")) {
            JOptionPane.showMessageDialog(this, 
                "Filtro aplicado: " + tipoFiltro + "\n" +
                "Libros encontrados: " + listaLibrosFiltrada.size(),
                "Resultados del Filtro", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para establecer la lista de libros (desde MenuUsuario)
    public void setListaLibros(List<Libro> libros) {
        this.listaLibrosCompleta = new ArrayList<>(libros);
        this.listaLibrosFiltrada = new ArrayList<>(libros);
        actualizarVistaLibros();
    }

    // add libros <<<<<
    // Este método se mantiene para compatibilidad
    public void agregarLibrosDeEjemplo() {
        for (int i = 1; i <= 5; i++) {
            this.add(new LibrosLista("default.png", "Libro Ejemplo " + i));
            this.add(Box.createVerticalStrut(20)); // Espacio
        }
    }
}