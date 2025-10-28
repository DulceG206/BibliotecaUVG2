package scr.Modelo;
import java.util.*;
import javax.swing.*;

import java.awt.*;

public class Carrusel extends JPanel {

    //VARIABLES----------------------------------------------------
    private CardPanel panelContenedor1 = new CardPanel("psicolo.jpg","Psicologia", Color.darkGray);
    private CardPanel panelContenedor2 = new CardPanel("ciencias.jpg","Ciencias", Color.darkGray);
    private CardPanel panelContenedor3 = new CardPanel("aventura.jpeg","Aventura", Color.darkGray);
    private CardPanel panelContenedor4 = new CardPanel("tecno.png","Tecnologia", Color.darkGray);
    private CardPanel panelContenedor5 = new CardPanel("mate.jpg","Matematicas", Color.darkGray);
    private CardPanel panelContenedor6 = new CardPanel("ficcion.png","Ciencia ficcion", Color.darkGray);
    private CardPanel panelContenedor7 = new CardPanel("historia.png","Historia", Color.darkGray);
    private CardPanel panelContenedor8 = new CardPanel("lenguas.jpg","Lenguas", Color.darkGray);


    public Carrusel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); 

        ImageIcon originalLogo = new ImageIcon("img/logoBiblioteca.png");
        Image imagenEscaladaLogo = originalLogo.getImage().getScaledInstance(190, 150, Image.SCALE_SMOOTH);
        ImageIcon imagenFinalLogo = new ImageIcon(imagenEscaladaLogo);
        JLabel etiquetaLogo = new JLabel(imagenFinalLogo);

        JLabel img2 = new JLabel("Imagen 2", JLabel.CENTER);
        img2.setOpaque(true);
        img2.setBackground(Color.PINK);

        JLabel img3 = new JLabel("Imagen 3", JLabel.CENTER);
        img3.setOpaque(true);
        img3.setBackground(Color.LIGHT_GRAY);

        /* 
        panelContenedor1.setBackground(Color.CYAN);
        panelContenedor2.setBackground(Color.white);
        panelContenedor3.setBackground(Color.red);
        panelContenedor4.setBackground(Color.blue);
        panelContenedor1.setBackground(Color.orange);
*/
        // Botones

        add(panelContenedor1);
        add(Box.createHorizontalStrut(20));
        add(panelContenedor2);
        add(Box.createHorizontalStrut(20));
        add(panelContenedor3);
        add(Box.createHorizontalStrut(20));
        add(panelContenedor4);
        add(Box.createHorizontalStrut(20));
        add(panelContenedor5);
        add(Box.createHorizontalStrut(20));
        add(panelContenedor6);
        add(Box.createHorizontalStrut(20));
        add(panelContenedor7);
        add(Box.createHorizontalStrut(20));
        add(panelContenedor8);

        // Timer opcional para rotar automáticamente cada 3 seg
        javax.swing.Timer timer = new javax.swing.Timer(3000, e -> mostrarSiguiente());
        timer.start();
    }

    private void mostrarSiguiente() {
    }

    private void mostrarAnterior() {
    }
}
