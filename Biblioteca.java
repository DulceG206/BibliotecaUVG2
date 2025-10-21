import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import com.formdev.flatlaf.FlatLightLaf;

import Administrador.MenuAdmin;
import Usuario.*;
// import Usuario.MenuUsuario; // Removed or update this line if MenuUsuario is in a different package
// If MenuUsuario is in the default package or another package, update the import accordingly, e.g.:
// import menu.MenuUsuario;
// Or remove the import if not needed and ensure MenuUsuario.java is in the same directory or package.

import com.formdev.flatlaf.FlatDarkLaf;

public class Biblioteca extends JFrame {

    // VARIABLES----------------------------------------------------
    ControladorLogReg controlador = new ControladorLogReg();

    private JTextField textFieldNombre = new JTextField();
    private JPasswordField passwordFieldContraseña = new JPasswordField();
    private JLabel labelNombre = new JLabel("Nombre");
    private JLabel labelContraseña = new JLabel("Contraseña");
    private JLabel labelDecoracion = new JLabel("________________________");
    private JCheckBox checkBoxMostrarContraseña = new JCheckBox("Mostrar Contraseña");
    private JButton buttonEntrar = new JButton("Entrar");
    private JButton buttonRegistrar = new JButton("Registrar");

    // IMAGENES---------------------------------------------------
    private ImageIcon originalLogo = new ImageIcon("img/logoBiblioteca.png");
    private Image imagenEscaladaLogo = originalLogo.getImage().getScaledInstance(500, 150, Image.SCALE_SMOOTH);
    private ImageIcon imagenFinalLogo = new ImageIcon(imagenEscaladaLogo);
    private JLabel etiquetaLogo = new JLabel(imagenFinalLogo);

    private ImageIcon originalLibros = new ImageIcon("img/inicio1.png");
    private Image imagenEscaladaLibros = originalLibros.getImage().getScaledInstance(1200, 720, Image.SCALE_SMOOTH);
    private ImageIcon imagenFinalLibros = new ImageIcon(imagenEscaladaLibros);
    private JLabel etiquetaLibros = new JLabel(imagenFinalLibros);

    private ImageIcon originalLibros2 = new ImageIcon("img/inicio2.png");
    private Image imagenEscaladaLibros2 = originalLibros2.getImage().getScaledInstance(1200, 720, Image.SCALE_SMOOTH);
    private ImageIcon imagenFinalLibros2 = new ImageIcon(imagenEscaladaLibros2);
    private JLabel etiquetaLibros2 = new JLabel(imagenFinalLibros2);

    // EJECUTABLE------------------------------------------------
    private Runnable centrarTodo = () -> {
        int centroX = (int) Math.round(this.getSize().getWidth() / 2);
        int centroY = (int) Math.round(this.getSize().getHeight() / 2);

        labelNombre.setLocation(
                centroX - (int) Math.round(labelNombre.getSize().getWidth() / 2),
                centroY - 90 - (int) Math.round(labelNombre.getSize().getHeight() / 2));

        labelContraseña.setLocation(
                centroX - (int) Math.round(labelContraseña.getSize().getWidth() / 2),
                centroY + 10 - (int) Math.round(labelContraseña.getSize().getHeight() / 2));

        textFieldNombre.setLocation(
                centroX - (int) Math.round(textFieldNombre.getSize().getWidth() / 2),
                centroY - 50 - (int) Math.round(textFieldNombre.getSize().getHeight() / 2));

        passwordFieldContraseña.setLocation(
                centroX - (int) Math.round(passwordFieldContraseña.getSize().getWidth() / 2),
                centroY + 50 - (int) Math.round(passwordFieldContraseña.getSize().getHeight() / 2));

        checkBoxMostrarContraseña.setLocation(
                centroX - (int) Math.round(checkBoxMostrarContraseña.getSize().getWidth() / 2),
                centroY + 100 - (int) Math.round(checkBoxMostrarContraseña.getSize().getHeight() / 2));

        buttonEntrar.setLocation(
                centroX - 100 - (int) Math.round(buttonEntrar.getSize().getWidth() / 2),
                centroY + 150 - (int) Math.round(buttonEntrar.getSize().getHeight() / 2));

        buttonRegistrar.setLocation(
                centroX + 100 - (int) Math.round(buttonRegistrar.getSize().getWidth() / 2),
                centroY + 150 - (int) Math.round(buttonRegistrar.getSize().getHeight() / 2));

        etiquetaLogo.setLocation(
                centroX - (int) Math.round(etiquetaLogo.getSize().getWidth() / 2),
                centroY - 220 - (int) Math.round(etiquetaLogo.getSize().getHeight() / 2));

        etiquetaLibros.setLocation(
                centroX + 900 - (int) Math.round(etiquetaLibros.getSize().getWidth() / 2),
                centroY - 10 - (int) Math.round(etiquetaLibros.getSize().getHeight() / 2));

        etiquetaLibros2.setLocation(
                centroX - 900 - (int) Math.round(etiquetaLibros2.getSize().getWidth() / 2),
                centroY - 10 - (int) Math.round(etiquetaLibros2.getSize().getHeight() / 2));

        labelDecoracion.setLocation(
                centroX - (int) Math.round(labelDecoracion.getSize().getWidth() / 2),
                centroY - 155 - (int) Math.round(labelDecoracion.getSize().getHeight() / 2));

    };

    /*
     * CONSTRUCTOR
     * aqui se escribe el codigo visual
     * =============================================================================
     */
    Biblioteca(String Titulo) {

        // SetSize------------------------------------------------------
        labelNombre.setSize(200, 20);
        labelContraseña.setSize(200, 20);
        labelDecoracion.setSize(620, 80);
        textFieldNombre.setSize(200, 20);
        passwordFieldContraseña.setSize(200, 20);
        checkBoxMostrarContraseña.setSize(200, 20);
        buttonEntrar.setSize(170, 32);
        buttonRegistrar.setSize(170, 32);
        etiquetaLogo.setSize(500, 150);
        etiquetaLibros.setSize(1200, 720);
        etiquetaLibros2.setSize(1200, 720);

        // FRAME--------------------------------------------------------
        this.setTitle(Titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 720);
        this.setMinimumSize(new Dimension(250, 300));
        this.setLayout(null);

        // CENTRAR-----------------------------------------------------
        centrarTodo.run();

        // ESTILO------------------------------------------------------
        // fonts<<<
        Font fontMediano = new Font("Arial", Font.PLAIN, 16);
        Font fontGrande = new Font("Arial", Font.PLAIN, 18);
        Font fontExtraGrandeVerde = new Font("Arial", Font.PLAIN, 41);
        // >>>
        labelNombre.setFont(fontGrande);
        labelContraseña.setFont(fontGrande);
        labelDecoracion.setFont(fontExtraGrandeVerde);
        textFieldNombre.setFont(fontMediano);
        passwordFieldContraseña.setFont(fontMediano);
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

        // ADD-----------------------------------------------------------
        this.add(labelNombre);
        this.add(labelContraseña);
        this.add(labelDecoracion);
        this.add(textFieldNombre);
        this.add(passwordFieldContraseña);
        this.add(checkBoxMostrarContraseña);
        this.add(buttonEntrar);
        this.add(buttonRegistrar);
        this.add(etiquetaLogo);
        this.add(etiquetaLibros);
        this.add(etiquetaLibros2);

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
                Registro registro = new Registro("Biblioteca UVG - Registro");
                registro.setVisible(true);
                dispose();
            }
        });

        buttonEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlador.verificarCuentas(textFieldNombre.getText(),
                        String.valueOf(passwordFieldContraseña.getPassword())) == false) {
                        JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos", "Error de Inicio de Sesión",
                            JOptionPane.ERROR_MESSAGE);
                    return;

                } else {
                    if (controlador.esEstudiante(textFieldNombre.getText(),
                        String.valueOf(passwordFieldContraseña.getPassword()))) {
                        MenuUsuario menuUsuario = new MenuUsuario("Biblioteca UVG - Usuario");
                        menuUsuario.setVisible(true);
                        dispose();
                    }else{
                        MenuAdmin menuUsuario = new MenuAdmin("Biblioteca UVG - Usuario");
                        menuUsuario.setVisible(true);
                        dispose();
                    }
                    
                }

            }
        });
    }

    public static void main(String[] args) throws IOException {

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.err.println("No se pudo aplicar FlatLaf");
        }

        Biblioteca biblioteca = new Biblioteca("Biblioteca UVG");
        biblioteca.setVisible(true);

    }

}
