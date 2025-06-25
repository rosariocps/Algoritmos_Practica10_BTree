package btree;

public class BTree<E extends Comparable<E>> {
    public BNode<E> root;
    private int order;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.order = orden;
        this.root = null;
        this.up = false;
        this.nDes = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int size() {
        return this.order;
    }

    public void insert(E cl) {
        up = false;
        E mediana;
        mediana = push(this.root, cl);
        if (up) {
            BNode<E> pnew = new BNode<E>(this.order);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        int pos[] = new int[1];
        E mediana;

        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean fl = current.searchNode(cl, pos);
            if (fl) {
                System.out.println("Item duplicado\n");
                up = false;
                return null;
            }

            mediana = push(current.childs.get(pos[0]), cl);

            if (up) {
                if (current.nodeFull(this.order - 1)) {
                    mediana = dividedNode(current, mediana, pos[0]);
                } else {
                    putNode(current, mediana, nDes, pos[0]);
                    up = false;
                }
            }
            return mediana;
        }
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        int i;

        for (i = current.count - 1; i >= k; i--) {

            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i));
        }


        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;

        // Calcular posición de la mediana
        posMdna = (k <= this.order / 2) ? this.order / 2 : this.order / 2 + 1;
        nDes = new BNode<E>(this.order);

        // Mover claves y hijos al nuevo nodo
        for (i = posMdna; i < this.order - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }

        nDes.count = (this.order - 1) - posMdna;
        current.count = posMdna;

        // Insertar el nuevo elemento en el nodo correspondiente
        if (k <= this.order / 2) {
            putNode(current, cl, rd, k);
        } else {
            putNode(nDes, cl, rd, k - posMdna);
        }

        // Obtener la mediana que subirá
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;

        return median;
    }

    public void remove(E cl) {
        delete(root, cl);
        if (root != null && root.count == 0) {
            root = root.childs.get(0);
        }
    }

    public boolean delete(BNode<E> node, E key) {
        if (node == null) {
            return false;
        }

        int pos[] = new int[1];
        boolean found = node.searchNode(key, pos);

        if (found) {
            if (node.childs.get(pos[0]) != null) {
                removeKey(node, pos[0]);
                return true;
            } else {
                E pred = getPredecessor(node, pos[0]);
                node.keys.set(pos[0], pred);
                return delete(node.childs.get(pos[0]), pred);
            }
        } else {
            if (node.childs.get(pos[0]) != null) {
                return false;
            } else {
                boolean isDeleted = delete(node.childs.get(pos[0]), key);
                if (node.childs.get(pos[0]).count < (order - 1) / 2) {
                    fix(node, pos[0]);
                }
                return isDeleted;
            }
        }
    }

    private void removeKey(BNode<E> node, int index) {
        for(int i = index; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
        }
        node.keys.set(node.count - 1, null);
        node.count--;
    }

    private E getPredecessor(BNode<E> node, int index) {
        BNode<E> current = node.childs.get(index);
        while (current.childs.get(index + 1) != null) {
            current = current.childs.get(index + 1);
        }
        return current.keys.get(current.count - 1);
    }

    private void merge(BNode<E> parent, int index) {
        BNode<E> left = parent.childs.get(index);
        BNode<E> right = parent.childs.get(index + 1);

        // Mover clave del padre al hijo izquierdo
        left.keys.set(left.count, parent.keys.get(index));
        left.count++;

        // Mover claves e hijos del hijo derecho al izquierdo
        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count + i, right.keys.get(i));
        }
        for (int i = 0; i <= right.count; i++) {
            left.childs.set(left.count + i, right.childs.get(i));
        }
        left.count += right.count;

        // Eliminar la clave del padre y el hijo derecho
        for (int i = index; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }

        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }

    private void borrowFromLeft(BNode<E> parent, int index ) {
        BNode<E> left = parent.childs.get(index - 1);
        BNode<E> current = parent.childs.get(index);

        // Desplazar claves e hijos del niño a la derecha
        for (int i = current.count; i >= 0; i--) {
            current.keys.set(i + 1, current.keys.get(i));
        }

        // Mover clave del padre al niño
        current.keys.set(0, parent.keys.get(index - 1));
        parent.keys.set(index - 1, left.keys.get(left.count - 1));
        left.keys.set(left.count - 1, null);

        // Mover hijo del hermano al niño
        if (left.childs.get(left.count) != null) {

            for (int i = current.count; i >= 0; i--) {
                current.childs.set(i + 1, current.childs.get(i));
            }
            current.childs.set(0, left.childs.get(left.count));
            left.childs.set(left.count, null);
        }

        current.count++;
        left.count--;
    }

    private void borrowFromRight(BNode<E> parent, int index) {
        BNode<E> right = parent.childs.get(index + 1);
        BNode<E> current = parent.childs.get(index);

        // Insertar clave del padre al final de current
        current.keys.set(current.count, parent.keys.get(index));

        // Reemplazar clave del padre con la primera del hermano derecho
        parent.keys.set(index, right.keys.get(0));

        // Desplazar las claves del hermano derecho una posición a la izquierda
        for (int i = 1; i < right.count; i++) {
            right.keys.set(i - 1, right.keys.get(i));
        }
        right.keys.set(right.count - 1, null);

        // Si tiene hijos, mover el primer hijo del hermano derecho al final de current
        if (right.childs.get(0) != null) {
            current.childs.set(current.count + 1, right.childs.get(0));
            for (int i = 1; i <= right.count; i++) {
                right.childs.set(i - 1, right.childs.get(i));
            }
            right.childs.set(right.count, null);
        }

        current.count++;
        right.count--;
    }

    private void fix(BNode<E> parent, int index) {
        if (index > 0 && parent.childs.get(index - 1).count > (order - 1) / 2) {
            borrowFromLeft(parent, index);
        }

        else if (index < parent.count && parent.childs.get(index + 1).count > (order - 1) / 2) {
            borrowFromRight(parent, index);
        }

        else {
            if (index > 0) {
                merge(parent, index - 1);
            } else {
                merge(parent, index);
            }
        }

    }

    @Override
    public String toString() {
        String s = "";
        if (isEmpty()) {
            s += "BTree is empty...";
        } else {
            s += "Id.Nodo | Claves Nodo     | Id.Padre | Id.Hijos\n";
            s += writeTree(this.root, null);
        }
        return s;
    }

    private String writeTree(BNode<E> current, BNode<E> parent) {
        if (current == null) return "";

        StringBuilder sb = new StringBuilder();

        // Id.Nodo
        sb.append(current.getIdNode()).append("       | ");

        // Claves Nodo
        for (int i = 0; i < current.count; i++) {
            sb.append(current.keys.get(i));
            if (i < current.count - 1) sb.append(", ");
        }

        // Id.Padre
        sb.append("     | ");
        sb.append((parent != null) ? parent.getIdNode() : "--");

        // Id.Hijos
        sb.append("     | [");
        boolean first = true;
        for (BNode<E> child : current.childs) {
            if (child != null) {
                if (!first) sb.append(", ");
                sb.append(child.getIdNode());
                first = false;
            }
        }
        sb.append("]\n");

        // Recorrer hijos válidos
        for (BNode<E> child : current.childs) {
            if (child != null) {
                sb.append(writeTree(child, current));
            }
        }

        return sb.toString();
    }

    public void printIndentedTree() {
        printIndentedTree(this.root, 0);
    }

    private void printIndentedTree(BNode<E> node, int level) {
        if (node == null) return;

        String indent = "    ".repeat(level);
        System.out.print(indent + "Nodo " + node.getIdNode() + " -> [");
        for (int i = 0; i < node.count; i++) {
            System.out.print(node.keys.get(i));
            if (i < node.count - 1) System.out.print(", ");
        }
        System.out.println("]");

        for (int i = 0; i <= node.count; i++) {
            if (node.childs.get(i) != null) {
                printIndentedTree(node.childs.get(i), level + 1);
            }
        }
    }

    public boolean search(E cl) {
        return searchRecursive(this.root, cl);
    }

    private boolean searchRecursive(BNode<E> node, E cl) {
        if (node == null) return false;

        int[] pos = new int[1];
        boolean found = node.searchNode(cl, pos);

        if (found) {
            System.out.println(cl + " se encuentra en el nodo " + node.getIdNode() + " en la posición " + pos[0]);
            return true;
        } else {
            return searchRecursive(node.childs.get(pos[0]), cl);
        }
    }
  
}
