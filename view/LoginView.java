package view;

import controller.LoginController;
import javax.swing.*;  // 🔥 IMPORTANTE: agregar este import

public class LoginView extends JFrame {

    public LoginView() {
        setTitle("Login Biblioteca");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Iniciar sesión");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Usuario:"));
        panel.add(userField);
        panel.add(new JLabel("Contraseña:"));
        panel.add(passField);
        panel.add(loginBtn);

        add(panel);

        loginBtn.addActionListener(e -> {
            LoginController controller = new LoginController();
            if (controller.iniciarSesion(userField.getText(), new String(passField.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Bienvenido!");

                MainView main = new MainView();  // 🔥 ahora sí se puede usar
                main.setVisible(true);

                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Datos incorrectos");
            }
        });
    }
}


