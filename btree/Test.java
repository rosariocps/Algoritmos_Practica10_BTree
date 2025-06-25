package btree;

import exceptions.ExceptionIsEmpty;
import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;

public class Test {

    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(4);

        try {
            tree.insert(50);
            System.out.println(tree);
            tree.insert(60);
            System.out.println(tree);
            tree.insert(1);
            System.out.println(tree);
            tree.insert(30);  // Overflow, se toma el de la derecha (50)
            System.out.println(tree);
            tree.insert(40);
            System.out.println(tree);
            tree.insert(70);
            System.out.println(tree);
            tree.insert(80);
            System.out.println(tree);
            tree.insert(90);
            System.out.println(tree);
            tree.insert(100);
            System.out.println(tree);
            tree.insert(95);
            System.out.println(tree);
            tree.insert(99);
            System.out.println(tree);
            tree.insert(106);
            System.out.println(tree);
            tree.insert(140);
            System.out.println(tree);
            tree.insert(120);

            System.out.println(tree);

        } catch (ItemDuplicated e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }

        System.out.println("\nÁrbol B (impresión jerárquica):");
        tree.printIndentedTree();

        System.out.println("\nResultado búsqueda:");
        
        try {
            tree.search(30);
            tree.search(100);
        } catch (ExceptionIsEmpty e) {
            System.err.println("No se pudo realizar la búsqueda: " + e.getMessage());
        }

        try {
            System.out.println("\nEliminando 40...");
            tree.remove(40);

            System.out.println("Eliminando 95...");
            tree.remove(95);

            System.out.println("Eliminando 300 (no existe)...");
            tree.remove(300); // Esto lanzará ItemNotFound

        } catch (ItemNotFound e) {
            System.err.println("⚠️ Error al eliminar: " + e.getMessage());
        }

        System.out.println("\nÁrbol B actualizado tras eliminaciones:");
        tree.printIndentedTree();
    }
}
