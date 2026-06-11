package especificacion;

public interface ABBTDA {
    void inicializarArbol();

    void agregarElem(int var1);

    void eliminarElem(int var1);

    int raiz();

    ABBTDA hijoIzq();

    ABBTDA hijoDer();

    boolean arbolVacio();
}
