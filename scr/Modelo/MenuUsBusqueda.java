package scr.Modelo;


import javax.swing.*;

import scr.Controlador.controladorBusqueda;
import scr.Vista.Biblioteca;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.awt.event.*;

public class MenuUsBusqueda extends JPanel {

    // VARIABLES----------------------------------------------------
    JPanel panelBarraBusqueda = new JPanel();
    JTextField textFieldBuscador = new JTextField();
    JButton buttonBuscar = new JButton("Buscar");
    JButton buttonFiltrar = new JButton("Filtrar");
    JButton buttonFinal = new JButton("Botón al Final");

    public MenuUsBusqueda() {

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
            this.add(new LibrosLista("default.png","default"));
            this.add(Box.createVerticalStrut(20)); // Espacio
        }
        // >>>>
        this.add(Box.createVerticalStrut(20)); // Espacio
        this.add(buttonFinal);
        // -------------------------------

        // FIN PANEL BUSQUEDA--------------------------------
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {





                            

                 
                Component[] components = MenuUsBusqueda.this.getComponents();
                java.util.List<Component> toRemove = new ArrayList<>();
                
                for (Component comp : components) {
                    if (comp instanceof LibrosLista) {
                        toRemove.add(comp);
                    }
                }
                for (Component comp : toRemove) {
                    remove(comp);
                }
                
                // ACTUALIZAR
                revalidate();
                repaint();

                
                controladorBusqueda control = new controladorBusqueda();
                List<String[][]> libros = control.leerLibros();
                if (libros != null) {
                    for (int i = 0; i < libros.size(); i++) {
                        String[][] libro = libros.get(i);
                        if (libro == null) continue;
                        for (int r = 0; r < libro.length; r++) {
                            String[] fila = libro[r];
                            if (fila == null) continue;
                            for (int c = 0; c < fila.length; c++) {
                                System.out.println("libros[" + i + "][" + r + "][" + c + "] = " + fila[c]);
                            }
                        }
                    }
                }



            }
        });

    }

}
