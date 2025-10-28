package scr.Modelo;
import java.util.*;
import javax.swing.*;
import java.awt.*;

class CardPanel extends JPanel {
    public JLabel titulo;
    
    public CardPanel(String imagen, String texto, Color color) {
        this.setBackground(color);
        this.setPreferredSize(new Dimension(130, 140)); 
        this.setLayout(new BorderLayout());
        //comentario totalmente importante
        ImageIcon originalLogo = new ImageIcon("img/"+imagen);
        Image imagenEscaladaLogo = originalLogo.getImage().getScaledInstance(90, 100, Image.SCALE_SMOOTH);
        ImageIcon imagenFinalLogo = new ImageIcon(imagenEscaladaLogo);
        JLabel etiquetaLogo = new JLabel(imagenFinalLogo);

        titulo = new JLabel(texto, JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.PLAIN, 16));
        this.add(etiquetaLogo, BorderLayout.NORTH);
        this.add(titulo, BorderLayout.CENTER);
    }

    public void setTitulo(String texto) {
        titulo.setText(texto);
    }
}
