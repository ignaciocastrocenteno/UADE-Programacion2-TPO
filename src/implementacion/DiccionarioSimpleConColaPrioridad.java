package implementacion;

import especificacion.DiccionarioSimpleTDA;
import especificacion.ColaPrioridadTDA;
import especificacion.ConjuntoTDA;

public class DiccionarioSimpleConColaPrioridad implements DiccionarioSimpleTDA {
    private ColaPrioridadTDA colaP;

    public void inicializarDiccionario() {
        colaP = new ColaPrioridadDin(); // C
        colaP.inicializarCola(); // C
    }

    public void agregar(int clave, int valor) {
        // En un diccionario, las claves son unicas. Por lo tanto, si la clave ya existia, la removemos primero.
        eliminar(clave); // L

        // Insertamos en la cola priorizando segun la 'clave', y el dato 'valor' se guarda en el contenido
        colaP.acolarPrioridad(valor, clave); // C
    }

    public void eliminar(int clave) {
        // Usamos una cola auxiliar para volcar todo excepto el elemento a eliminar
        ColaPrioridadTDA aux = new ColaPrioridadDin(); // C
        aux.inicializarCola(); // C
        
        // Iteramos sacando elementos uno por uno
        while (!colaP.colaVacia()) { // L
            // Si la prioridad (que usamos de clave) NO es la que queremos eliminar, la guardamos
            if (colaP.prioridad() != clave) { // C
                aux.acolarPrioridad(colaP.primero(), colaP.prioridad()); // C
            }

            // Avanzamos destruyendo el tope actual
            colaP.desacolar(); // C
        }
        
        // Restauramos los elementos retenidos de vuelta a la cola original
        while (!aux.colaVacia()) { // L
            colaP.acolarPrioridad(aux.primero(), aux.prioridad()); // C
            aux.desacolar(); // C
        }
    }

    public int recuperar(int clave) {
        // Cola auxiliar para reconstruir la estructura sin perder datos
        ColaPrioridadTDA aux = new ColaPrioridadDin(); // C
        aux.inicializarCola(); // C
        int res = 0; // C
        
        // Vaciamos la cola buscando la clave pedida
        while (!colaP.colaVacia()) { // L
            // Cuando la encontramos, guardamos su valor asociado (el campo 'primero')
            if (colaP.prioridad() == clave) { // C
                res = colaP.primero(); // C
            }

            // Independientemente de si coincide o no, conservamos todos los elementos
            aux.acolarPrioridad(colaP.primero(), colaP.prioridad()); // C
            colaP.desacolar(); // C
        }
        
        // Retornamos todos los elementos a su estructura original
        while (!aux.colaVacia()) { // L
            colaP.acolarPrioridad(aux.primero(), aux.prioridad()); // C
            aux.desacolar(); // C
        }
        return res; // C
    }

    public ConjuntoTDA claves() {
        ConjuntoTDA conj = new ConjuntoDin(); // C
        conj.inicializarConjunto(); // C
        ColaPrioridadTDA aux = new ColaPrioridadDin(); // C
        aux.inicializarCola(); // C
        
        // Extraemos destructivamente pero guardando en auxiliar
        while (!colaP.colaVacia()) { // L
            // Las prioridades representan nuestras claves, por lo que las agrupamos en un conjunto
            conj.agregar(colaP.prioridad()); // L
            aux.acolarPrioridad(colaP.primero(), colaP.prioridad()); // C
            colaP.desacolar(); // C
        }
        
        // Reponemos todo a la cola de prioridad original para mantener la inmutabilidad de la estructura base
        while (!aux.colaVacia()) { // L
            colaP.acolarPrioridad(aux.primero(), aux.prioridad()); // C
            aux.desacolar(); // C
        }
        return conj; // C
    }
}
