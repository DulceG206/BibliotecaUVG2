package view;

import javax.swing.*;

public class MainView extends JFrame {

    public MainView() {
        setTitle("Menú Principal - Biblioteca");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton librosBtn = new JButton("Apartar libros");
        JButton salonesBtn = new JButton("Apartar salones");
        JButton pcBtn = new JButton("Apartar computadoras");

        JPanel panel = new JPanel();
        panel.add(librosBtn);
        panel.add(salonesBtn);
        panel.add(pcBtn);

        add(panel);

        librosBtn.addActionListener(e -> new LibrosView().setVisible(true));
        salonesBtn.addActionListener(e -> new SalonesView().setVisible(true));
        pcBtn.addActionListener(e -> new PCView().setVisible(true));
    }
}

