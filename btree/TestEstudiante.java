package btree;

public class TestEstudiante {
    public static void main(String[] args) {
        BTreeEstudiante gestor = new BTreeEstudiante(4); // orden 4

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
        gestor.insertar(new RegistroEstudiante(108, "Juan")); // Duplicado, no se insertará

        System.out.println("Árbol B generado:");
        gestor.imprimir();

        System.out.println("\nOperaciones de búsqueda:");
        System.out.println("Buscar 115 → " + gestor.buscarNombre(115)); // David
        System.out.println("Buscar 132 → " + gestor.buscarNombre(132)); // Ernesto
        System.out.println("Buscar 999 → " + gestor.buscarNombre(999)); // No encontrado

        System.out.println("\nEliminando estudiante con código 101...");
        gestor.eliminar(101);

        System.out.println("\nInsertando nuevo estudiante: (106, Sara)");
        gestor.insertar(new RegistroEstudiante(106, "Sara"));

        System.out.println("\nBuscar 106 → " + gestor.buscarNombre(106)); // Sara

        System.out.println("\nÁrbol B final:");
        gestor.imprimir();
    }
}
