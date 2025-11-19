package scr.Modelo;
import java.util.*;
import javax.swing.*;
import java.awt.*;

/*No tocar Plisssssssss
    si quieren cambiar algo de aqui mejor hagan una nueva clase
    esto es solo para mostrar libros en forma de lista

*/
public class LibrosLista extends JPanel{
    public LibrosLista(String imagen, String titulo){

        setBounds(10,0,700,2);
        setBackground(Color.GRAY);

        //setLayout(new BorderLayout());+

        ImageIcon originalLogo = new ImageIcon("img/" + imagen);
        Image imagenEscaladaLogo = originalLogo.getImage().getScaledInstance(170, 110, Image.SCALE_SMOOTH);
        ImageIcon imagenFinalLogo = new ImageIcon(imagenEscaladaLogo);
        JLabel etiquetaLogo = new JLabel(imagenFinalLogo);
        etiquetaLogo.setSize(170, 110);

        JLabel tituloL = new JLabel("titulo");
        JButton boton = new JButton("Abrir");
        
        add(etiquetaLogo, BorderLayout.PAGE_START);
        add(tituloL, BorderLayout.CENTER);
        add(boton, BorderLayout.SOUTH);
    }
}
