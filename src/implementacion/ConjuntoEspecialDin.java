package implementacion;

import especificacion.ConjuntoEspecialTDA;
import especificacion.ConjuntoTDA;

public class ConjuntoEspecialDin implements ConjuntoEspecialTDA {
    private ConjuntoTDA c;

    public void inicializarConjunto() {
        c = new ConjuntoDin(); // C
        c.inicializarConjunto(); // C
    }

    public Respuesta agregar(int valor) {
        // Inicializamos el objeto Respuesta que contendra el estado de la operacion
        Respuesta r = new Respuesta(); // C
        // Verificamos si el elemento ya esta presente para evitar duplicados y marcar el error
        if (c.pertenece(valor)) { // L
            // Como el valor ya existe, la consigna exige marcar el error en true
            r.error = true; // C
        } else {
            // Caso contrario, delegamos la insercion real al TDA base dinamico
            c.agregar(valor); // C
            r.error = false; // C
        }
        return r; // C
    }

    public Respuesta sacar(int valor) {
        Respuesta r = new Respuesta(); // C
        // Antes de eliminar, confirmamos la existencia del valor
        if (c.pertenece(valor)) { // L
            // Si existe, lo sacamos del conjunto base dinamico y confirmamos exito
            c.sacar(valor); // L
            r.error = false; // C
        } else {
            // Si no existia, no se puede sacar, por ende hay error
            r.error = true; // C
        }
        return r; // C
    }

    public Respuesta elegir() {
        Respuesta r = new Respuesta(); // C
        // Controlamos la precondicion: no se puede elegir de un conjunto vacio
        if (c.conjuntoVacio()) { // C
            r.error = true; // C
        } else {
            // Si hay elementos, delegamos la eleccion aleatoria/arbitraria y guardamos el dato
            r.rta = c.elegir(); // C
            r.error = false; // C
        }
        return r; // C
    }

    public boolean pertenece(int valor) {
        return c.pertenece(valor); // L
    }

    public boolean conjuntoVacio() {
        return c.conjuntoVacio(); // C
    }
}
