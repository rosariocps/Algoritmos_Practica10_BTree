package btree;

public class Test {
    
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(4);

        tree.insert(50);
        System.out.println(tree);

        tree.insert(60);
        System.out.println(tree);

        tree.insert(1);
        System.out.println(tree);

        tree.insert(30);
        System.out.println(tree); //Overflow, se toma el de la derecha (50)

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

        System.out.println("\nÁrbol B (impresión jerárquica):");
        tree.printIndentedTree();

        System.out.println("\nResultado búsqueda:");
        tree.search(30);
        tree.search(100);

    }
}
