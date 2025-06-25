package btree;

public class Test {
    public static void main(String[] args) {
        BTree<RegistroEstudiante> arbol = new BTree<>(4);

        // Insertar estudiantes
        arbol.insert(new RegistroEstudiante(103, "Ana"));
        arbol.insert(new RegistroEstudiante(110, "Luis"));
        arbol.insert(new RegistroEstudiante(101, "Carlos"));
        arbol.insert(new RegistroEstudiante(120, "Lucía"));
        arbol.insert(new RegistroEstudiante(115, "David"));
        arbol.insert(new RegistroEstudiante(125, "Jorge"));
        arbol.insert(new RegistroEstudiante(140, "Camila"));
        arbol.insert(new RegistroEstudiante(108, "Rosa"));
        arbol.insert(new RegistroEstudiante(132, "Ernesto"));
        arbol.insert(new RegistroEstudiante(128, "Denis"));
        arbol.insert(new RegistroEstudiante(145, "Enrique"));
        arbol.insert(new RegistroEstudiante(122, "Karina"));
        arbol.insert(new RegistroEstudiante(108, "Juan")); // Duplicado, no se insertará

        System.out.println("----- BTree generado -----");
        System.out.println(arbol);

        // Operaciones de búsqueda
        System.out.println("Buscar 115: " + arbol.buscarNombre(115)); // David
        System.out.println("Buscar 132: " + arbol.buscarNombre(132)); // Ernesto
        System.out.println("Buscar 999: " + arbol.buscarNombre(999)); // No encontrado

        // Eliminar estudiante con código 101
        arbol.remove(new RegistroEstudiante(101, ""));

        // Insertar nuevo estudiante 106 - Sara
        arbol.insert(new RegistroEstudiante(106, "Sara"));

        // Buscar 106
        System.out.println("Buscar 106: " + arbol.buscarNombre(106));
    }
}
