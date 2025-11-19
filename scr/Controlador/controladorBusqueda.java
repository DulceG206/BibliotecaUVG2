package scr.Controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class controladorBusqueda {

    public List<String[][]> leerLibros() {
        List<String[][]> baseDatos = new ArrayList<>();

        BufferedReader lectura;
        try {
            lectura = new BufferedReader(new FileReader("Libros.txt"));

            String linea;
            while ((linea = lectura.readLine()) != null) {
                String[] datos = linea.split("¬");
                String[][] libro = new String[datos.length][1];
                
                for (int i = 0; i < datos.length; i++) {
                    libro[i][0] = datos[i];
                }
                
                baseDatos.add(libro);
            }

            lectura.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baseDatos;
    }
    
    
}
