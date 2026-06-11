package implementacion;

import especificacion.ConjuntoMamushkaTDA;
import especificacion.DiccionarioSimpleTDA;
import especificacion.ConjuntoTDA;

public class ConjuntoMamushkaDin implements ConjuntoMamushkaTDA {
    private DiccionarioSimpleTDA d;

    public void inicializar() {
        d = new DiccionarioSimpleDin(); // C
        d.inicializarDiccionario(); // C
    }

    public void guardar(int dato) {
        int cant = 0; // C
        // Usamos las claves del diccionario base para verificar pertenencia
        ConjuntoTDA claves = d.claves(); // L
        if (claves.pertenece(dato)) { // L
            // Si el dato ya fue guardado previamente, recuperamos su frecuencia
            cant = d.recuperar(dato); // L

            // Eliminamos la clave para obligar su reemplazo con el contador actualizado
            d.eliminar(dato); // L
        }
        // Insertamos el dato actualizando su frecuencia sumandole 1 a la cantidad anterior (o inicial en 0)
        d.agregar(dato, cant + 1); // L
    }

    public void sacar(int dato) {
        ConjuntoTDA claves = d.claves(); // L
        // Validamos que el elemento exista antes de descontar
        if (claves.pertenece(dato)) { // L
            // Obtenemos cuantas instancias hay del dato
            int cant = d.recuperar(dato); // L

            // Lo quitamos temporalmente (o definitivamente si cant es 1)
            d.eliminar(dato); // L

            // Si habia mas de una instancia, lo volvemos a guardar pero descontando 1
            if (cant > 1) { // C
                d.agregar(dato, cant - 1); // L
            }
        }
    }

    public int elegir() {
        // Elegir un valor del ConjuntoMamushka es equivalente a elegir una clave arbitraria del Diccionario interno
        ConjuntoTDA claves = d.claves(); // L
        return claves.elegir(); // C
    }

    public int perteneceCant(int dato) {
        ConjuntoTDA claves = d.claves(); // L
        // Chequeamos existencia, si pertenece retornamos la frecuencia guardada como valor
        if (claves.pertenece(dato)) { // L
            return d.recuperar(dato); // L
        }
        // Si no pertenece, su cantidad de apariciones es logicamente 0
        return 0; // C
    }

    public boolean estaVacio() {
        ConjuntoTDA claves = d.claves(); // L
        return claves.conjuntoVacio(); // C
    }
}
