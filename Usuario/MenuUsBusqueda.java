package Usuario;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class MenuUsBusqueda extends JPanel {

    // VARIABLES----------------------------------------------------
    JPanel panelBarraBusqueda = new JPanel();
    JTextField textFieldBuscador = new JTextField();
    JButton buttonBuscar = new JButton("Buscar");
    JButton buttonFiltrar = new JButton("Filtrar");
    JButton buttonFinal = new JButton("Botón al Final");

    MenuUsBusqueda() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // layaout de lista

        // panel barra de busqueda <<<<<

        textFieldBuscador.setPreferredSize(new Dimension(400, 40));
        buttonBuscar.setPreferredSize(new Dimension(90, 40));
        buttonFiltrar.setPreferredSize(new Dimension(90, 40));
        buttonFinal.setPreferredSize(new Dimension(90, 40));
        buttonBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
        buttonFiltrar.setFont(new Font("Arial", Font.PLAIN, 16));
        buttonFinal.setFont(new Font("Arial", Font.PLAIN, 16));

        // ADD BUSQUEDA--------------------------------
        // add barra busqueda <<<<<
        panelBarraBusqueda.add(textFieldBuscador);
        panelBarraBusqueda.add(buttonBuscar);
        panelBarraBusqueda.add(Box.createHorizontalStrut(100));
        panelBarraBusqueda.add(buttonFiltrar);
        this.add(panelBarraBusqueda);
        // >>>>
        // add libros <<<<<
        for (int i = 1; i <= 20; i++) {
            this.add(new LibrosLista("default.png"));
            this.add(Box.createVerticalStrut(20)); // Espacio
        }
        // >>>>
        this.add(Box.createVerticalStrut(20)); // Espacio
        this.add(buttonFinal);
        // -------------------------------

        // FIN PANEL BUSQUEDA--------------------------------

    }

}
