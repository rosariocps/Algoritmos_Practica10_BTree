package btree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargarArbolB {
    public static void cargarDesdeArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int numeroLinea = 0;

            // Leer la primera línea como orden del árbol
            linea = br.readLine();
            numeroLinea++;
            if (linea == null || linea.trim().isEmpty()) {
                throw new RuntimeException("El archivo está vacío o la primera línea está en blanco.");
            }

            int orden = Integer.parseInt(linea.trim());
            System.out.println("Orden del árbol B: " + orden);

            // Leer el resto de líneas como nodos
            while ((linea = br.readLine()) != null) {
                numeroLinea++;
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(",");

                if (partes.length < 3) {
                    throw new RuntimeException("Línea mal formateada (línea " + numeroLinea + "): " + linea);
                }

                int nivel = Integer.parseInt(partes[0].trim());
                int idNodo = Integer.parseInt(partes[1].trim());

                List<Integer> claves = new ArrayList<>();
                for (int i = 2; i < partes.length; i++) {
                    claves.add(Integer.parseInt(partes[i].trim()));
                }

                // Mostrar para verificación
                System.out.println("Línea " + numeroLinea + ": Nivel = " + nivel +
                        ", ID Nodo = " + idNodo + ", Claves = " + claves);
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error de formato numérico: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String ruta = "C:/A_RO_ARCHIVES/UNI/Semestre 5/Algoritmos/Practica10_ArbolB/btree/arbolB.txt";
        cargarDesdeArchivo(ruta);
    }
}
