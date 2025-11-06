package scr.Vista;

import javax.swing.*;
import scr.Modelo.MenuUsBusqueda;
import scr.Modelo.MenuUsInicio;
import scr.Modelo.MenuUsSalones;
import scr.Modelo.MenuUsComputadoras;
import scr.Modelo.Libro;
import scr.Modelo.Usuario;
import scr.Modelo.LibrosLista;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class MenuUsuario extends JFrame {

    // VARIABLES---
    // panel izquierdo
    JPanel panelIzquierdo = new JPanel();
    JButton botoncambio = new JButton("Inicio");
    JButton botoncambio2 = new JButton("Buscar");
    JButton botoncambio3 = new JButton("Apartar");
    JButton botoncambio4 = new JButton("Configuraciones");
    // NUEVOS BOTONES
    JButton botoncambio5 = new JButton("Salones");
    JButton botoncambio6 = new JButton("Computadoras");

    JLabel labelTitulo = new JLabel("BIENVENIDO A LA BIBLIOTECA UVG");

    // panel derecho
    CardLayout cardBusqueda = new CardLayout(); // cardlayout para contener los menus
    JPanel panelContenedor = new JPanel(cardBusqueda); // Jpanel contenedor del lado derecho

    // panel derecho - menu de inicio
    MenuUsInicio menulinicio;

    // panel derecho - menu configuraciones
    JPanel panelConfiguraciones = new JPanel();

    // panel derecho - menu de busqueda
    MenuUsBusqueda menubusqueda = new MenuUsBusqueda();
    JScrollPane scrollPane = new JScrollPane(menubusqueda);// colocar barra escroleable para ver mas lista

    // panel de libros apartados
    JPanel panelApartados = new JPanel(new BorderLayout());
    JTextArea areaApartados = new JTextArea();
    JScrollPane scrollApartados = new JScrollPane(areaApartados);

    // NUEVOS PANELES
    // panel derecho - menu de salones
    MenuUsSalones menuSalones;

    // panel derecho - menu de computadoras  
    MenuUsComputadoras menuComputadoras;

    // lista de libros disponibles
    private java.util.List<Libro> listaLibros = new ArrayList<>();

    // usuario actual
    private Usuario usuarioActual;

    Runnable centrarTodo = () -> {
    };

    public MenuUsuario(String titulo) {
        // FRAME---
        this.setTitle(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 720);
        this.setMinimumSize(new Dimension(250, 300));
        this.setLocationRelativeTo(null);

        /*
         * PANEL IZQUIERDO
         * ===============================================================
         */

        botoncambio.setPreferredSize(new Dimension(200, 50));
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS)); // layout de lista
        panelIzquierdo.setPreferredSize(new Dimension(200, 720));

        JPanel panelContenedorOut = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelContenedorOut.setMaximumSize(new Dimension(200, 100));

        ImageIcon originalLogo = new ImageIcon("img/logo.png");
        Image imagenEscaladaLogo = originalLogo.getImage().getScaledInstance(90, 40, Image.SCALE_SMOOTH);
        ImageIcon imagenFinalLogo = new ImageIcon(imagenEscaladaLogo);
        JLabel etiquetaLogo = new JLabel(imagenFinalLogo);

        ImageIcon originalLogo1 = new ImageIcon("img/cerrar-sesion.png");
        Image imagenEscaladaLogo1 = originalLogo1.getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon imagenFinalLogo1 = new ImageIcon(imagenEscaladaLogo1);
        JLabel etiquetaLogo1 = new JLabel(imagenFinalLogo1);

        labelTitulo.setFont(new Font("Arial", Font.PLAIN, 18));

        // CONFIGURAR TODOS LOS BOTONES INCLUYENDO LOS NUEVOS
        JButton[] botones = { botoncambio, botoncambio2, botoncambio3, botoncambio4, botoncambio5, botoncambio6 };

        for (JButton boton : botones) {
            boton.setFont(new Font("Arial", Font.PLAIN, 16));
            boton.setPreferredSize(new Dimension(200, 40));
            boton.setMaximumSize(new Dimension(200, 40));
            boton.setAlignmentX(CENTER_ALIGNMENT);
        }

        panelContenedorOut.add(etiquetaLogo);
        panelContenedorOut.add(Box.createHorizontalStrut(20));
        panelContenedorOut.add(etiquetaLogo1);

        panelIzquierdo.add(panelContenedorOut);
        panelIzquierdo.add(Box.createVerticalStrut(20));
        panelIzquierdo.add(botoncambio);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(botoncambio2);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(botoncambio3);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(botoncambio4);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        // AGREGAR NUEVOS BOTONES AL PANEL
        panelIzquierdo.add(botoncambio5);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(botoncambio6);
        panelIzquierdo.add(Box.createVerticalGlue());

        /*
         * PANEL DERECHO
         * ===============================================================
         */

        // Panel INICIO---
        menulinicio = new MenuUsInicio(panelContenedor, this);

        // Panel CONFIGURACIONES---
        // (mantener existente)

        // Panel BUSQUEDA---
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Panel APARTADOS
        areaApartados.setEditable(false);
        areaApartados.setFont(new Font("Arial", Font.PLAIN, 14));
        panelApartados.add(scrollApartados, BorderLayout.CENTER);

        // INICIALIZAR NUEVOS PANELES
        // Crear usuario temporal para pruebas (en un sistema real esto vendría del login)
        usuarioActual = new Usuario("usuario_ejemplo", "Usuario de Prueba");
        menuSalones = new MenuUsSalones(usuarioActual);
        menuComputadoras = new MenuUsComputadoras();

        // AGREGAR TODOS LOS PANELES AL CONTENEDOR
        panelContenedor.add(menulinicio, "Menu1");
        panelContenedor.add(scrollPane, "Menu2");
        panelContenedor.add(panelApartados, "Menu3");
        panelContenedor.add(panelConfiguraciones, "Menu4");
        // AGREGAR NUEVOS PANELES
        panelContenedor.add(menuSalones, "Menu5");
        panelContenedor.add(menuComputadoras, "Menu6");

        // ADD
        getContentPane().add(panelIzquierdo, BorderLayout.WEST);// panel izquierdo
        getContentPane().add(panelContenedor, BorderLayout.CENTER);// panel derecho

        // LISTENERS---
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (getSize().width < 800) {
                    centrarTodo.run();
                    panelIzquierdo.setVisible(false);
                } else {
                    centrarTodo.run();
                    panelIzquierdo.setVisible(true);
                }
            }
        });

        etiquetaLogo1.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Biblioteca biblio = new Biblioteca("Biblioteca UVG");
                biblio.setVisible(true);
                dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
            }

        });

        // inicializar algunos libros de ejemplo
        inicializarLibros();

        // conectar el panel de búsqueda con los libros
        menubusqueda.setListaLibros(listaLibros);

        // botones de navegación
        botoncambio.addActionListener(e -> cardBusqueda.show(panelContenedor, "Menu1"));
        botoncambio2.addActionListener(e -> cardBusqueda.show(panelContenedor, "Menu2"));
        botoncambio3.addActionListener(e -> {
            actualizarLibrosApartados();
            cardBusqueda.show(panelContenedor, "Menu3");
        });
        botoncambio4.addActionListener(e -> cardBusqueda.show(panelContenedor, "Menu4"));
        // AGREGAR LISTENERS PARA NUEVOS BOTONES
        botoncambio5.addActionListener(e -> cardBusqueda.show(panelContenedor, "Menu5"));
        botoncambio6.addActionListener(e -> cardBusqueda.show(panelContenedor, "Menu6"));
    }

    // método para inicializar libros de ejemplo
    private void inicializarLibros() {
        listaLibros.add(new Libro("El Principito", "Antoine de Saint-Exupéry"));
        listaLibros.add(new Libro("Cien años de soledad", "Gabriel García Márquez"));
        listaLibros.add(new Libro("1984", "George Orwell"));
        listaLibros.add(new Libro("Don Quijote", "Miguel de Cervantes"));
    }

    // método para actualizar la vista de libros apartados
    public void actualizarLibrosApartados() {
        if (usuarioActual == null) return;

        StringBuilder sb = new StringBuilder("📝 Libros apartados por: " + usuarioActual.getNombre() + "\n\n");

        for (Libro libro : usuarioActual.getLibrosApartados()) {
            sb.append(libro.toString()).append("\n");
        }

        areaApartados.setText(sb.toString());
    }

    // asignar usuario actual
    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }
}