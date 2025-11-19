package view;

import controller.BibliotecaController;
import model.Salon;

import javax.swing.*;

public class SalonesView extends JFrame {

    public SalonesView() {
        setTitle("Apartar Salones");
        setSize(400, 300);
        setLocationRelativeTo(null);

        BibliotecaController controller = new BibliotecaController();
        Salon[] salones = controller.obtenerSalones();

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Salon s : salones) {
            model.addElement(s.getNombre());
        }

        JList<String> lista = new JList<>(model);
        JButton apartarBtn = new JButton("Apartar");

        apartarBtn.addActionListener(e -> {
            int index = lista.getSelectedIndex();
            if (index != -1) {
                salones[index].apartar();
                JOptionPane.showMessageDialog(this, "Salón apartado");
            }
        });

        add(new JScrollPane(lista), "Center");
        add(apartarBtn, "South");
    }
}
