package Usuario;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;


public class MenuUsuario extends JFrame {

    // VARIABLES----------------------------------------------------
    // panel izquierdo
    JPanel panelIzquierdo = new JPanel();
    JButton botoncambio = new JButton("Inicio");
    JButton botoncambio2 = new JButton("Buscar");
    JButton botoncambio3 = new JButton("Apartar");
    JButton botoncambio4 = new JButton("Configuraciones");
    JLabel labelTitulo = new JLabel("BIENVENIDO A LA BIBLIOTECA UVG");

    // panel derecho
    CardLayout cardBusqueda = new CardLayout(); // cardlayout para contener los menus
    JPanel panelContenedor = new JPanel(cardBusqueda); // Jpanel contenedor del lado derecho

    // panel derecho - menu de inicio
    MenuUsInicio menuInicio;

    // panel derecho - menu configuraciones
    JPanel panelConfiguraciones = new JPanel();

    // panel derecho - menu de busqueda
    MenuUsBusqueda menubusqueda = new MenuUsBusqueda();
    JScrollPane scrollPane = new JScrollPane(menubusqueda);// colocar barra escroleable para ver mas lista

    // Emmmmm pos no quiten el runnable alli miro si lo quito :v
    Runnable centrarTodo = () -> {

    };

    public MenuUsuario(String titulo) {
        // FRAME--------------------------------------------------------
        this.setTitle(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 720);
        this.setMinimumSize(new Dimension(250, 300));
        this.setLocationRelativeTo(null);

        /*
         * PANEL IZQUIERDO
         * ============================================================================
         */
        botoncambio.setPreferredSize(new Dimension(200, 50));
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS)); // layaout de lista
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
        JButton[] botones = { botoncambio, botoncambio2, botoncambio3, botoncambio4 };
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
        panelIzquierdo.add(Box.createVerticalGlue());

        /*
         * PANEL DERECHO
         * =======================================================================
         */
        // Panel INICIO--------------------------------------------
        menuInicio = new MenuUsInicio(panelContenedor, this);
        // FIN PANEL INICIO-----------------------------------------

        // PANEL CONFIGURACIONES--------------------------------------------

        // FIN PANEL CONFIGURACIONES-----------------------------------------

        // PANEL BUSQUEDA------------------------------------------
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // FIN PANEL BUSQUEDA--------------------------------

        // ADD
        // CONTENDOR---------------------------------------------------------------------
        panelContenedor.add(menuInicio, "Menu1");
        panelContenedor.add(scrollPane, "Menu2");
        panelContenedor.add(panelConfiguraciones, "Menu3");

        // ADD
        getContentPane().add(panelIzquierdo, BorderLayout.WEST);// panel izquierdo
        getContentPane().add(panelContenedor, BorderLayout.CENTER);// panel derecho

        // LISTENERS------------------------------------------------------------
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
        botoncambio.addActionListener(e -> cardBusqueda.show(panelContenedor, "Menu1"));
        botoncambio2.addActionListener(e -> cardBusqueda.show(panelContenedor, "Menu2"));
        botoncambio3.addActionListener(e -> cardBusqueda.show(panelContenedor, "Menu3"));

    }
}
