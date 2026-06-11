package especificacion;

public interface ConjuntoTDA {
    void inicializarConjunto();

    void agregar(int var1);

    void sacar(int var1);

    int elegir();

    boolean pertenece(int var1);

    boolean conjuntoVacio();
}
