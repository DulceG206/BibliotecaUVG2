package Usuario;
import java.util.*;
import javax.swing.*;
import java.awt.*;

class CardPanel extends JPanel {
    private JLabel titulo;
    
    public CardPanel(String imagen, String texto, Color color) {
        setBackground(color);
        setPreferredSize(new Dimension(130, 140)); 
        setLayout(new BorderLayout());

        ImageIcon originalLogo = new ImageIcon("img/"+imagen);
        Image imagenEscaladaLogo = originalLogo.getImage().getScaledInstance(90, 100, Image.SCALE_SMOOTH);
        ImageIcon imagenFinalLogo = new ImageIcon(imagenEscaladaLogo);
        JLabel etiquetaLogo = new JLabel(imagenFinalLogo);

        titulo = new JLabel(texto, JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.PLAIN, 16));
        add(etiquetaLogo, BorderLayout.NORTH);
        add(titulo, BorderLayout.CENTER);
    }

    public void setTitulo(String texto) {
        titulo.setText(texto);
    }
}
