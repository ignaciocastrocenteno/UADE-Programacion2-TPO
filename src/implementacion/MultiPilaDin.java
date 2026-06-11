package implementacion;

import especificacion.MultiPilaTDA;
import especificacion.PilaTDA;

public class MultiPilaDin implements MultiPilaTDA {
    private PilaTDA p;

    public void inicializarPila() {
        p = new PilaDin(); // C
        p.inicializarPila(); // C
    }

    public void apilar(PilaTDA valores) {
        // Usamos dos pilas auxiliares: aux para invertir los valores, y aux2 para conservar la pila original
        PilaTDA aux = new PilaDin(); // C
        aux.inicializarPila(); // C
        PilaTDA aux2 = new PilaDin(); // C
        aux2.inicializarPila(); // C
        
        // Vaciamos la pila de valores recibida, apilando tanto en aux como en aux2
        while (!valores.pilaVacia()) { // L
            aux.apilar(valores.tope()); // C
            aux2.apilar(valores.tope()); // C
            valores.desapilar(); // C
        }
        
        // Restauramos la pila 'valores' original utilizando aux2 (que actua como espejo temporal)
        while (!aux2.pilaVacia()) { // L
            valores.apilar(aux2.tope()); // C
            aux2.desapilar(); // C
        }
        
        // Ahora desapilamos aux hacia nuestra MultiPila.
        // Como aux contiene los elementos invertidos, al apilarlos de nuevo retomaran el orden original exigido
        while (!aux.pilaVacia()) { // L
            p.apilar(aux.tope()); // C
            aux.desapilar(); // C
        }
    }

    public void desapilar(PilaTDA valores) {
        // Copiar 'valores' a 'auxValores' manteniendo estrictamente el orden.
        // Primero invertimos la pila valores en 'aux'
        PilaTDA aux = new PilaDin(); // C
        aux.inicializarPila(); // C
        while (!valores.pilaVacia()) { // L
            aux.apilar(valores.tope()); // C
            valores.desapilar(); // C
        }
        
        // Luego desapilamos 'aux' rellenando tanto 'auxValores' (nuestra copia a comparar) como 'valores' (restauracion)
        PilaTDA auxValores = new PilaDin(); // C
        auxValores.inicializarPila(); // C
        while (!aux.pilaVacia()) { // L
            auxValores.apilar(aux.tope()); // C
            valores.apilar(aux.tope()); // C
            aux.desapilar(); // C
        }
        
        // Bandera booleana para emular un corte de ciclo seguro sin usar 'break'
        boolean coincide = true; // C
        // Pila para resguardar los topes que vayamos sacando de la MultiPila durante la verificacion
        PilaTDA auxP = new PilaDin(); // C
        auxP.inicializarPila(); // C
        
        // Comparamos secuencialmente tope contra tope
        while (!auxValores.pilaVacia() && coincide) { // L
            // Si la multipila se queda sin elementos antes de terminar, o si los topes difieren, cortamos
            if (p.pilaVacia() || p.tope() != auxValores.tope()) { // C
                coincide = false; // C
            } else {
                // Si coinciden, desapilamos guardando el estado en auxP por si falla despues
                auxP.apilar(p.tope()); // C
                p.desapilar(); // C
                auxValores.desapilar(); // C
            }
        }
        
        // Si la bandera paso a false, significa que la sub-pila no estaba en el tope de la MultiPila.
        // Debemos deshacer las modificaciones (rollback) usando nuestra pila de resguardo auxP
        if (!coincide) { // C
            // Restaurar la multipila p a su estado inicial
            while (!auxP.pilaVacia()) { // L
                p.apilar(auxP.tope()); // C
                auxP.desapilar(); // C
            }
        }
    }

    public PilaTDA tope(int cantidad) {
        // Creamos la pila resultante (res) y un auxiliar para extraer temporalmente los topes
        PilaTDA aux = new PilaDin(); // C
        aux.inicializarPila(); // C
        PilaTDA res = new PilaDin(); // C
        res.inicializarPila(); // C
        
        int cont = 0; // C
        // Extraemos elementos hasta llegar al limite estipulado o quedarnos sin valores
        while (!p.pilaVacia() && cont < cantidad) { // L
            aux.apilar(p.tope()); // C
            p.desapilar(); // C
            cont++; // C
        }
        
        // Volcamos lo extraido tanto en la estructura original para restaurarla,
        // como en la estructura respuesta (res). Esto, de paso, reinvierte los elementos a su orden natural
        while (!aux.pilaVacia()) { // L
            res.apilar(aux.tope()); // C
            p.apilar(aux.tope()); // C
            aux.desapilar(); // C
        }
        
        return res; // C
    }

    public boolean pilaVacia() {
        return p.pilaVacia(); // C
    }
}
