package btree;

import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {
    // Contador estático para asignar IDs únicos a cada nodo
    private static int counter = 0;
    private int idNode;               // Identificador del nodo
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;

    public BNode(int n) {
        this.idNode = ++counter;

        this.keys   = new ArrayList<>(n);
        this.childs = new ArrayList<>(n);
        this.count  = 0;
        for (int i = 0; i < n; i++) {
            this.keys .add(null);
            this.childs.add(null);
        }
    }

    public boolean nodeFull(int maxKeys) {
        return this.count == maxKeys;
    }

    public boolean nodeEmpty() {
        return this.count == 0;
    }

    public boolean searchNode(E key, int[] pos) {
        int i = 0;
        while (i < count && key.compareTo(keys.get(i)) > 0) {
            i++;
        }
        pos[0] = i;
        return (i < count && key.compareTo(keys.get(i)) == 0);
    }

    public int getIdNode() {
        return idNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ID");
        sb.append(idNode).append(": [");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

