import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Registro extends JFrame {
    
        // VARIABLES----------------------------------------------------
        JTextField textFieldNombre = new JTextField();
        JPasswordField passwordFieldContraseña = new JPasswordField();
        JPasswordField passwordFieldConfirmar = new JPasswordField();
        JLabel labelNombre = new JLabel("Nombre");
        JLabel labelContraseña = new JLabel("Contraseña");
        JLabel labelConfirmarContraseña = new JLabel("Confirmar Contraseña");
        JLabel labelDecoracion = new JLabel("________________________");
        JCheckBox checkBoxMostrarContraseña = new JCheckBox("Mostrar Contraseña");
        JButton buttonEntrar = new JButton("Confirmar");
        JButton buttonRegistrar = new JButton("Iniciar Sesion");

    public Registro(String Titulo) {
        // FRAME--------------------------------------------------------
        this.setTitle(Titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 720);
        this.setMinimumSize(new Dimension(250, 300));
        this.setLayout(null);

        //IMAGENES---------------------------------------------------
        ImageIcon originalLogo = new ImageIcon("img/logoBiblioteca.png");
        Image imagenEscaladaLogo = originalLogo.getImage().getScaledInstance(500, 150, Image.SCALE_SMOOTH);
        ImageIcon imagenFinalLogo = new ImageIcon(imagenEscaladaLogo);
        JLabel etiquetaLogo = new JLabel(imagenFinalLogo);

        Runnable centrarTodo = () -> {
            int centroX = (int) Math.round(this.getSize().getWidth() / 2);
            int centroY = (int) Math.round(this.getSize().getHeight() / 2);

            labelNombre.setLocation(
                    centroX - (int) Math.round(labelNombre.getSize().getWidth() / 2),
                    centroY - 170 - (int) Math.round(labelNombre.getSize().getHeight() / 2));

            labelContraseña.setLocation(
                    centroX - (int) Math.round(labelContraseña.getSize().getWidth() / 2),
                    centroY - 70 - (int) Math.round(labelContraseña.getSize().getHeight() / 2));

            textFieldNombre.setLocation(
                    centroX - (int) Math.round(textFieldNombre.getSize().getWidth() / 2),
                    centroY - 120 - (int) Math.round(textFieldNombre.getSize().getHeight() / 2));

            passwordFieldContraseña.setLocation(
                    centroX - (int) Math.round(passwordFieldContraseña.getSize().getWidth() / 2),
                    centroY - 20 - (int) Math.round(passwordFieldContraseña.getSize().getHeight() / 2));

            checkBoxMostrarContraseña.setLocation(
                    centroX - (int) Math.round(checkBoxMostrarContraseña.getSize().getWidth() / 2),
                    centroY + 130 - (int) Math.round(checkBoxMostrarContraseña.getSize().getHeight() / 2));

            buttonEntrar.setLocation(
                    centroX - 100 - (int) Math.round(buttonEntrar.getSize().getWidth() / 2),
                    centroY + 180 - (int) Math.round(buttonEntrar.getSize().getHeight() / 2));

            buttonRegistrar.setLocation(
                    centroX + 100 - (int) Math.round(buttonRegistrar.getSize().getWidth() / 2),
                    centroY + 180 - (int) Math.round(buttonRegistrar.getSize().getHeight() / 2));

            labelConfirmarContraseña.setLocation(
                    centroX - (int) Math.round(labelConfirmarContraseña.getSize().getWidth() / 2),
                    centroY + 30 - (int) Math.round(labelConfirmarContraseña.getSize().getHeight() / 2));

            passwordFieldConfirmar.setLocation(
                    centroX - (int) Math.round(passwordFieldConfirmar.getSize().getWidth() / 2),
                    centroY + 80 - (int) Math.round(passwordFieldConfirmar.getSize().getHeight() / 2));
                
            labelDecoracion.setLocation(
                    centroX - (int) Math.round(labelDecoracion.getSize().getWidth() / 2),
                    centroY - 210 - (int) Math.round(labelDecoracion.getSize().getHeight() / 2));

            etiquetaLogo.setLocation(
                    centroX - (int) Math.round(etiquetaLogo.getSize().getWidth() / 2),
                    centroY - 280 - (int) Math.round(etiquetaLogo.getSize().getHeight() / 2));
        };

        // SetSize------------------------------------------------------
        labelNombre.setSize(200, 20);
        labelContraseña.setSize(200, 20);
        labelConfirmarContraseña.setSize(200, 20);
        labelDecoracion.setSize(620, 80);
        textFieldNombre.setSize(200, 20);
        passwordFieldContraseña.setSize(200, 20);
        checkBoxMostrarContraseña.setSize(200, 20);
        passwordFieldConfirmar.setSize(200, 20);
        buttonEntrar.setSize(170, 32);
        buttonRegistrar.setSize(170, 32);
        etiquetaLogo.setSize(500, 150);

         // LOCATION-----------------------------------------------------
        centrarTodo.run();

        // ESTILO------------------------------------------------------
        // fonts<<<
        Font fontMediano = new Font("Arial", Font.PLAIN, 16);
        Font fontGrande = new Font("Arial", Font.PLAIN, 18);
        Font fontExtraGrandeVerde = new Font("Arial", Font.PLAIN, 41);
        // >>>
        labelNombre.setFont(fontGrande);
        labelContraseña.setFont(fontGrande);
        labelConfirmarContraseña.setFont(fontGrande);
        labelDecoracion.setFont(fontExtraGrandeVerde);
        textFieldNombre.setFont(fontMediano);
        passwordFieldContraseña.setFont(fontMediano);
        passwordFieldConfirmar.setFont(fontMediano);
        checkBoxMostrarContraseña.setFont(fontMediano);
        buttonEntrar.setFont(fontMediano);
        buttonRegistrar.setFont(fontMediano);

        // colors<<<
        labelDecoracion.setForeground(new Color(23, 163, 90));

        // botones<<<
        buttonRegistrar.setBorder(null);
        buttonRegistrar.putClientProperty("JButton.buttonType", "roundRect");
        buttonRegistrar.setBackground(new Color(23, 163, 90));
        buttonRegistrar.setForeground(Color.WHITE);

        buttonEntrar.setBorder(null);
        buttonEntrar.putClientProperty("JButton.buttonType", "roundRect");
        buttonEntrar.setBackground(new Color(23, 163, 90));
        buttonEntrar.setForeground(Color.WHITE);

        // Extra<<<
        labelDecoracion.setHorizontalAlignment(SwingConstants.CENTER);

        //ADD------------------------------------------------------------
        this.add(labelNombre);
        this.add(labelContraseña);
        this.add(labelConfirmarContraseña);
        this.add(labelDecoracion);
        this.add(textFieldNombre);
        this.add(passwordFieldContraseña);
        this.add(passwordFieldConfirmar);
        this.add(checkBoxMostrarContraseña);
        this.add(buttonEntrar);
        this.add(buttonRegistrar);
        this.add(etiquetaLogo);

        // LISTENERS-----------------------------------------------------
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                centrarTodo.run();
            }
        });
        checkBoxMostrarContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxMostrarContraseña.isSelected()) {
                    passwordFieldContraseña.setEchoChar((char) 0);
                } else {
                    passwordFieldContraseña.setEchoChar('•');
                }
            }
        });
        buttonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Biblioteca biblioteca = new Biblioteca("Biblioteca UVG");
                biblioteca.setVisible(true);
                dispose();
            }
        });
        buttonEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rootPaneCheckingEnabled) {
                        
                } else {
                        
                }
            }
        });


        // VISIBLE------------------------------------------------------

        
    }
    
}
