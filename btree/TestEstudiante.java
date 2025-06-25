package btree;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;

public class TestEstudiante {
    public static void main(String[] args) {
        BTreeEstudiante gestor = new BTreeEstudiante(4); // orden 4

        try {
            // Insertar estudiantes
            gestor.insertar(new RegistroEstudiante(103, "Ana"));
            gestor.insertar(new RegistroEstudiante(110, "Luis"));
            gestor.insertar(new RegistroEstudiante(101, "Carlos"));
            gestor.insertar(new RegistroEstudiante(120, "Lucía"));
            gestor.insertar(new RegistroEstudiante(115, "David"));
            gestor.insertar(new RegistroEstudiante(125, "Jorge"));
            gestor.insertar(new RegistroEstudiante(140, "Camila"));
            gestor.insertar(new RegistroEstudiante(108, "Rosa"));
            gestor.insertar(new RegistroEstudiante(132, "Ernesto"));
            gestor.insertar(new RegistroEstudiante(128, "Denis"));
            gestor.insertar(new RegistroEstudiante(145, "Enrique"));
            gestor.insertar(new RegistroEstudiante(122, "Karina"));
            gestor.insertar(new RegistroEstudiante(108, "Juan")); // Duplicado
        } catch (ItemDuplicated e) {
            System.err.println("⚠️ Error al insertar: " + e.getMessage());
        }

        System.out.println("Árbol B generado:");
        gestor.imprimir();

        System.out.println("\nOperaciones de búsqueda:");
        System.out.println("Buscar 115 → " + gestor.buscarNombre(115)); // David
        System.out.println("Buscar 132 → " + gestor.buscarNombre(132)); // Ernesto
        System.out.println("Buscar 999 → " + gestor.buscarNombre(999)); // No encontrado

        System.out.println("\nEliminando estudiante con código 101...");
        try {
            gestor.eliminar(101);
        } catch (ItemNotFound e) {
            System.err.println("⚠️ No se pudo eliminar: " + e.getMessage());
        }

        System.out.println("\nInsertando nuevo estudiante: (106, Sara)");
        try {
            gestor.insertar(new RegistroEstudiante(106, "Sara"));
        } catch (ItemDuplicated e) {
            System.err.println("⚠️ Error al insertar a Sara: " + e.getMessage());
        }

        System.out.println("\nBuscar 106 → " + gestor.buscarNombre(106)); // Sara

        System.out.println("\nÁrbol B final:");
        gestor.imprimir();
    }
}
