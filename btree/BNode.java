package btree;

import java.util.ArrayList;

public class BNode<E>{
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;

    public BNode(int n) {
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n);
        this.count = 0;
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
        this.childs.add(null);
    }

    public boolean nodeFull() {
        return false;
    }

    public boolean nodeEmpty() {
        return false;
    }

    public boolean searchNode() {
        return false;
    }

    public String toString() {
        return "";
    }
}
