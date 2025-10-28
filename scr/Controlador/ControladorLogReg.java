package scr.Controlador;
import java.io.*;
import java.util.*;

public class ControladorLogReg {

    public List<String[]> leerDatos() {
        List<String[]> baseDatos = new ArrayList<>();

        BufferedReader lectura;
        try {
            lectura = new BufferedReader(new FileReader("BaseDatos.txt"));

            String linea = new String();

            linea = lectura.readLine();
            while (linea != null) {

                linea = linea.substring(0, linea.length());

                baseDatos.add(linea.split(":"));
                linea = lectura.readLine();
            }

            lectura.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Test
        // String[] fila : baseDatos
        /*
         * for (int i = 0; i < baseDatos.size(); i++) {
         * System.out.println("Usuario: " + baseDatos.get(i)[0] + "  Contraseña: " +
         * baseDatos.get(i)[1] + "  Extra: "
         * + baseDatos.get(i)[2]);
         * }
         */
        return baseDatos;
    }

    public Boolean verificarCuentas(String inputUsuario, String inputContraseña) {
        List<String[]> baseDatos = leerDatos();

        for (int i = 0; i < baseDatos.size(); i++) {
            if (baseDatos.get(i)[0].equals(inputUsuario) && baseDatos.get(i)[1].equals(inputContraseña)) {
                return true;
            }
        }
        return false;
    }

    public Boolean esEstudiante(String inputUsuario, String inputContraseña) {
        List<String[]> baseDatos = leerDatos();

        for (int i = 0; i < baseDatos.size(); i++) {
            if (baseDatos.get(i)[0].equals(inputUsuario) && baseDatos.get(i)[1].equals(inputContraseña)
                    && baseDatos.get(i)[2].equals("estudiante")) {
                return true;
            }
        }
        return false;
    }

    public void guardarUsuario(String nombre, String contraseña) {

        try (FileWriter writer = new FileWriter("BaseDatos.txt", true)) {
            writer.write((nombre + ":" + contraseña + ":estudiante" + System.lineSeparator()));
        } catch (IOException e) {
        }
    }
    public boolean usuarioRepetido(String usuario){
        List<String[]> baseDatos = leerDatos();

        for (int i = 0; i < baseDatos.size(); i++) {
            if (usuario.equals(baseDatos.get(i)[0])) {
                return true;
            }
        }
        return false;
    }
}
