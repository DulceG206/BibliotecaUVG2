package view;

import controller.BibliotecaController;
import model.Computadora;

import javax.swing.*;

public class PCView extends JFrame {

    public PCView() {
        setTitle("Apartar Computadoras");
        setSize(400, 300);
        setLocationRelativeTo(null);

        BibliotecaController controller = new BibliotecaController();
        Computadora[] pcs = controller.obtenerComputadoras();

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Computadora pc : pcs) {
            model.addElement("Computadora " + pc.getNumero());
        }

        JList<String> lista = new JList<>(model);
        JButton apartarBtn = new JButton("Apartar");

        apartarBtn.addActionListener(e -> {
            int index = lista.getSelectedIndex();
            if (index != -1) {
                pcs[index].apartar();
                JOptionPane.showMessageDialog(this, "Computadora apartada");
            }
        });

        add(new JScrollPane(lista), "Center");
        add(apartarBtn, "South");
    }
}
