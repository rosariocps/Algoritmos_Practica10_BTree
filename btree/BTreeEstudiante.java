package btree;

public class BTreeEstudiante {
    private BTree<RegistroEstudiante> arbol;

    public BTreeEstudiante(int orden) {
        arbol = new BTree<>(orden);
    }

    public void insertar(RegistroEstudiante estudiante) {
        arbol.insert(estudiante);
    }

    public void eliminar(int codigo) {
        arbol.remove(new RegistroEstudiante(codigo, ""));
    }

    public String buscarNombre(int codigo) {
        RegistroEstudiante encontrado = buscarRecursivo(arbol.getRoot(), codigo);
        if (encontrado != null) return encontrado.getNombre();
        return "No encontrado";
    }

    private RegistroEstudiante buscarRecursivo(BNode<RegistroEstudiante> nodo, int codigo) {
        if (nodo == null) return null;

        for (int i = 0; i < nodo.count; i++) {
            RegistroEstudiante actual = nodo.keys.get(i);
            if (actual.getCodigo() == codigo) return actual;
            else if (codigo < actual.getCodigo())
                return buscarRecursivo(nodo.childs.get(i), codigo);
        }

        return buscarRecursivo(nodo.childs.get(nodo.count), codigo);
    }

    public void imprimir() {
        System.out.println(arbol.toString());
    }
}
