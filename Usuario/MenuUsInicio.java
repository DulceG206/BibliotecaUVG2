package Usuario;
import javax.swing.*;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class MenuUsInicio extends JPanel {

    // VARIABLES-----------------------------------------------------
    ImageIcon originalLogo = new ImageIcon("img/logoBiblioteca.png");
    Image imagenEscaladaLogo = originalLogo.getImage().getScaledInstance(200, 60, Image.SCALE_SMOOTH);
    ImageIcon imagenFinalLogo = new ImageIcon(imagenEscaladaLogo);
    JLabel etiquetaLogo = new JLabel(imagenFinalLogo);

    JPanel panelIndex = new JPanel();
    JButton buttonBuscar = new JButton("Buscar");
    JTextField textFieldBuscador = new JTextField();
    Carrusel carruselTipos = new Carrusel();
    JScrollPane scrollCarrusel = new JScrollPane(carruselTipos);// colocar barra escroleable para el carrusel

    Runnable centrarTodo = () -> {

    };

    MenuUsInicio(JPanel panelContenedor, JFrame frameContenedor) {
        textFieldBuscador.setPreferredSize(new Dimension(400, 40));
        buttonBuscar.setPreferredSize(new Dimension(90, 40));
        buttonBuscar.setFont(new Font("Arial", Font.PLAIN, 16));

        carruselTipos.setPreferredSize(new Dimension(1400, 160));

        scrollCarrusel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollCarrusel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollCarrusel.setPreferredSize(
                new Dimension(frameContenedor.getSize().width - panelContenedor.getSize().width - 220, 160));
        scrollCarrusel.setBorder(null);
        //scrollCarrusel.getVerticalScrollBar().setUnitIncrement(140); 

        panelIndex.add(etiquetaLogo);
        panelIndex.add(textFieldBuscador);
        panelIndex.add(buttonBuscar);

        etiquetaLogo.setAlignmentX(2);
        this.add(panelIndex);

        this.add(scrollCarrusel);
    }

    // LISTENERS-----------------------------------------------------


}
