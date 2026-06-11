package especificacion;

public interface ColaPrioridadTDA {
    void inicializarCola();

    void acolarPrioridad(int var1, int var2);

    void desacolar();

    int primero();

    int prioridad();

    boolean colaVacia();
}
