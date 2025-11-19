package view;

import controller.BibliotecaController;
import model.Libro;

import javax.swing.*;

public class LibrosView extends JFrame {

    public LibrosView() {
        setTitle("Apartar Libros");
        setSize(400, 300);
        setLocationRelativeTo(null);

        BibliotecaController controller = new BibliotecaController();
        Libro[] libros = controller.obtenerLibros();

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Libro l : libros) {
            model.addElement(l.getTitulo());
        }

        JList<String> lista = new JList<>(model);
        JButton apartarBtn = new JButton("Apartar");

        apartarBtn.addActionListener(e -> {
            int index = lista.getSelectedIndex();
            if (index != -1) {
                libros[index].apartar();
                JOptionPane.showMessageDialog(this, 
                    "Apartado. Devuelve en: " + libros[index].getFechaDevolucion());
            }
        });

        add(new JScrollPane(lista), "Center");
        add(apartarBtn, "South");
    }
}

