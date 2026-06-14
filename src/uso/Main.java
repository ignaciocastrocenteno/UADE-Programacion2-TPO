package uso;

import especificacion.*;
import implementacion.*;

public class Main {
    // Ejercicio 6
    public static float PorcentajeParesPila(PilaTDA pila){//l;
        //inicializo variables
        float porcentaje = 0; // c
        int cantidad = 0; //c 
        int cantidadPares = 0;//c
        PilaTDA aux = new PilaTDA();//c
        aux.inicializarPila();//c

        //recorro pila y cuento valores pares
        while(!pila.pilaVacia()){//l
            if(pila.tope() % 2 == 0){//c
                cantidadPares += 1;//c
            }
            cantidad += 1;//c
            aux.apilar(pila.tope());//c
            pila.desapilar();//c
        }
        //reingreso datos del auxiliar a la pila
        while(!aux.pilaVacia()){//l
            pila.apilar(aux.tope());//c
            aux.desapilar();//c
        }
        // defino porcentaje final
        porcentaje = (float) cantidadPares / cantidad * 100;//c
        return porcentaje;//c
    }
    // Ejercicio 7
    public static ConjuntoTDA ElementosRepetidos(PilaTDA pila){//p
        //inicializo auxiliares y variables
        ConjuntoTDA Repetidos = new ConjuntoTDA(); //c
        Repetidos.inicializarConjunto();//c
        ConjuntoTDA Originales = new ConjuntoTDA();//c
        Originales.inicializarConjunto();//c
        PilaTDA aux = new PilaTDA();//c
        aux.inicializarPila();//c

        //me fijo si el valor ya esta en el conjunto y si ya pertenece es porque esta repetido
        while(!pila.pilaVacia()){//p
            if(Originales.pertenece(pila.tope())){//l
                Repetidos.agregar(pila.tope());//l
            }else{//c
                Originales.agregar(pila.tope());//l
            }
            aux.apilar(pila.tope());//c
            pila.desapilar();//c
        }
        while(!aux.pilaVacia()){//l
            pila.apilar(aux.tope());//c
            aux.desapilar();//c
        }
        return Repetidos;//c
    } 

    // Ejercicio 8
    public static ColaTDA SinRepetidos(ColaTDA cola){//p
        //inicializo variables y auxiliares
        ConjuntoTDA Originales = new ConjuntoTDA();//c
        Originales.inicializarConjunto();//c
        ColaTDA ColaSinRepes = new ColaTDA();//c
        ColaSinRepes.inicializarCola();//c
        ColaTDA Original = new ColaTDA();//c
        Original.inicializarCola();//c

        //si no pertenece al conjunto se agrega al conjunto y a la cola sin repetidos y se guarda una copia del original
        while(!cola.colaVacia()){//p
            if(!Originales.pertenece(cola.primero())){//l
                Originales.agregar(cola.primero());//l
                ColaSinRepes.acolar(cola.primero());//l
            }
            Original.acolar(cola.primero());//l
            cola.desacolar();//c
        }
        //se recrea el original
        while(!Original.colaVacia()){//p
            cola.acolar(Original.primero());//l
            Original.desacolar();//c
        }
        return ColaSinRepes;//c
    }

    // Ejercicio 9: Conjunto con los elementos comunes de la pila y la cola
    public static ConjuntoTDA elementosComunes(PilaTDA pila, ColaTDA cola) {
        // Estrategia: pasamos todos los elementos de la cola a un conjunto de busqueda rapida (O(1) o O(N)).
        // Esto evita tener que recorrer toda la cola por cada elemento de la pila (O(N*M))
        ConjuntoTDA elementosCola = new ConjuntoDin();
        elementosCola.inicializarConjunto();
        ColaTDA auxCola = new ColaDin();
        auxCola.inicializarCola();
        
        while (!cola.colaVacia()) {
            int primero = cola.primero();
            elementosCola.agregar(primero);
            auxCola.acolar(primero);
            cola.desacolar();
        }
        // Restauramos la cola
        while (!auxCola.colaVacia()) {
            cola.acolar(auxCola.primero());
            auxCola.desacolar();
        }
        
        ConjuntoTDA comunes = new ConjuntoDin();
        comunes.inicializarConjunto();
        PilaTDA auxPila = new PilaDin();
        auxPila.inicializarPila();
        
        // Ahora recorremos la pila, y con un simple 'pertenece' detectamos las intersecciones
        while (!pila.pilaVacia()) {
            int tope = pila.tope();
            if (elementosCola.pertenece(tope)) {
                comunes.agregar(tope);
            }
            auxPila.apilar(tope);
            pila.desapilar();
        }
        // Restauramos la pila
        while (!auxPila.pilaVacia()) {
            pila.apilar(auxPila.tope());
            auxPila.desapilar();
        }
        
        return comunes;
    }

    // Ejercicio 10: Diccionario con la cantidad de apariciones de la pila
    public static DiccionarioSimpleTDA diccionarioFrecuencias(PilaTDA pila) {
        // En este diccionario guardaremos Dato -> Frecuencia
        DiccionarioSimpleTDA d = new DiccionarioSimpleDin();
        d.inicializarDiccionario();
        PilaTDA aux = new PilaDin();
        aux.inicializarPila();
        
        while (!pila.pilaVacia()) {
            int tope = pila.tope();
            ConjuntoTDA claves = d.claves();
            // Verificamos si ya habiamos procesado una instancia previa de este numero
            if (claves.pertenece(tope)) {
                // Si existe, recuperamos cuantas veces lo vimos, y actualizamos el diccionario (sacando e insertando)
                int cant = d.recuperar(tope);
                d.eliminar(tope);
                d.agregar(tope, cant + 1);
            } else {
                // Primer avistamiento del numero
                d.agregar(tope, 1);
            }
            aux.apilar(tope);
            pila.desapilar();
        }
        
        // Restauramos el orden y la estructura de la pila recibida
        while (!aux.pilaVacia()) {
            pila.apilar(aux.tope());
            aux.desapilar();
        }
        
        return d;
    }

    // Ejercicio 11: Cola con todos los valores de un diccionario multiple, sin repetir
    public static ColaTDA valoresUnicos(DiccionarioMultipleTDA dic) {
        // Estructuras de respuesta y control
        ConjuntoTDA unicos = new ConjuntoDin();
        unicos.inicializarConjunto();
        ColaTDA res = new ColaDin();
        res.inicializarCola();
        
        ConjuntoTDA claves = dic.claves();
        // Conjunto auxiliar para no mutar el conjunto de claves original devuelto por dic.claves()
        ConjuntoTDA auxClaves = new ConjuntoDin();
        auxClaves.inicializarConjunto();
        
        // Iteramos sobre cada clave existente
        while (!claves.conjuntoVacio()) {
            int clave = claves.elegir();
            ConjuntoTDA valores = dic.recuperar(clave);
            // Mismo patron anti-mutabilidad: auxiliar para no vaciar los valores de esta clave en el diccionario
            ConjuntoTDA auxValores = new ConjuntoDin();
            auxValores.inicializarConjunto();
            
            // Recorremos los multiples valores mapeados a esta clave especifica
            while (!valores.conjuntoVacio()) {
                int valor = valores.elegir();
                // Si el valor no se acolo jamas en el historial, lo agregamos y lo acoplamos
                if (!unicos.pertenece(valor)) {
                    unicos.agregar(valor);
                    res.acolar(valor);
                }
                auxValores.agregar(valor);
                valores.sacar(valor);
            }
            // Restaurar los valores en el conjunto asociado a la clave
            while (!auxValores.conjuntoVacio()) {
                int valor = auxValores.elegir();
                valores.agregar(valor);
                auxValores.sacar(valor);
            }
            
            auxClaves.agregar(clave);
            claves.sacar(clave);
        }
        // Restaurar las claves al conjunto inicial del diccionario
        while (!auxClaves.conjuntoVacio()) {
            int clave = auxClaves.elegir();
            claves.agregar(clave);
            auxClaves.sacar(clave);
        }
        return res;
    }

    // Ejercicio 12: Suma de elementos impares de un ABB
    public static int sumaImparesABB(ABBTDA abb) {
        // Base recursiva: arbol (o subarbol) nulo no aporta suma
        if (abb.arbolVacio()) {
            return 0;
        }
        int raiz = abb.raiz();
        // Calculamos el aporte del nodo actual evaluando su paridad
        int suma = (raiz % 2 != 0) ? raiz : 0;
        // Llamada recursiva: lo acumulado aca + la rama izquierda + la rama derecha
        return suma + sumaImparesABB(abb.hijoIzq()) + sumaImparesABB(abb.hijoDer());
    }

    // Ejericio 13: Cantidad de hojas con valor par de un ABB
    public static int hojasParesABB(ABBTDA abb) {
        // Base nula
        if (abb.arbolVacio()) {
            return 0;
        }
        // Condicion de hoja: no tiene hijo izquierdo ni derecho
        if (abb.hijoIzq().arbolVacio() && abb.hijoDer().arbolVacio()) {
            // Si es hoja y su valor es par, aporta 1 al contador global
            if (abb.raiz() % 2 == 0) {
                return 1;
            } else {
                return 0; // Si es hoja pero es impar, no cuenta
            }
        }
        // Si no es hoja, exploramos descendentemente ambos subarboles
        return hojasParesABB(abb.hijoIzq()) + hojasParesABB(abb.hijoDer());
    }

    // Ejercicio 14: Vertices puente
    public static ConjuntoTDA verticesPuente(GrafoTDA grafo, int o, int d) {
        // Un vertice es puente si existe una arista directa desde o hacia el puente, y desde el puente hacia d
        ConjuntoTDA puentes = new ConjuntoDin();
        puentes.inicializarConjunto();
        
        ConjuntoTDA vertices = grafo.vertices();
        ConjuntoTDA auxVertices = new ConjuntoDin();
        auxVertices.inicializarConjunto();
        
        // Evaluamos candidato por candidato
        while (!vertices.conjuntoVacio()) {
            int p = vertices.elegir();
            // Comprobamos la doble arista concurrente requerida para que sea puente
            if (grafo.existeArista(o, p) && grafo.existeArista(p, d)) {
                puentes.agregar(p);
            }
            auxVertices.agregar(p);
            vertices.sacar(p);
        }
        
        // Volvemos a colocar todos los vertices en el conjunto del grafo
        while (!auxVertices.conjuntoVacio()) {
            int p = auxVertices.elegir();
            vertices.agregar(p);
            auxVertices.sacar(p);
        }
        
        return puentes;
    }

    // Ejercicio 15: Grado de un vertice
    public static int gradoVertice(GrafoTDA grafo, int v) {
        // Grado general = Aristas Salientes - Aristas Entrantes
        int aristasSalientes = 0;
        int aristasEntrantes = 0;
        
        ConjuntoTDA vertices = grafo.vertices();
        ConjuntoTDA auxVertices = new ConjuntoDin();
        auxVertices.inicializarConjunto();
        
        // Recorremos todos los demas nodos para ver quienes conectan con 'v'
        while (!vertices.conjuntoVacio()) {
            int otro = vertices.elegir();
            // Validar existencia del camino V -> Otro
            if (grafo.existeArista(v, otro)) {
                aristasSalientes++;
            }
            // Validar existencia del camino Otro -> V
            if (grafo.existeArista(otro, v)) {
                aristasEntrantes++;
            }
            auxVertices.agregar(otro);
            vertices.sacar(otro);
        }
        
        // Restauramos los vertices
        while (!auxVertices.conjuntoVacio()) {
            int otro = auxVertices.elegir();
            vertices.agregar(otro);
            auxVertices.sacar(otro);
        }
        
        // La consigna indica restar las salientes con las entrantes
        return aristasSalientes - aristasEntrantes;
    }

    public static void main(String[] args) {
        System.out.println("--- Tests Ejecutando ---");
        
        // Probando el ejercicio 6
        PilaTDA p = new PilaDin();
        p.inicializarPila();
        p.apilar(2); p.apilar(3); p.apilar(4); p.apilar(2); p.apilar(5);
        System.out.println("Porcentaje Pares (Pila [2,3,4,2,5]): " + porcentajePares(p) + "%");
        
        // Probando el ejercicio 7
        ConjuntoTDA repetidos = repetidosPila(p);
        System.out.print("Repetidos Pila: ");
        while (!repetidos.conjuntoVacio()) {
            int val = repetidos.elegir();
            System.out.print(val + " ");
            repetidos.sacar(val);
        }
        System.out.println();
        
        // Probando el ejercicio 8
        ColaTDA c = new ColaDin();
        c.inicializarCola();
        c.acolar(1); c.acolar(2); c.acolar(1); c.acolar(3); c.acolar(2);
        ColaTDA cSR = colaSinRepetidos(c);
        System.out.print("Cola sin repetidos (de [1,2,1,3,2]): ");
        while (!cSR.colaVacia()) {
            System.out.print(cSR.primero() + " ");
            cSR.desacolar();
        }
        System.out.println();
        
        System.out.println("--- Todos los tests ejecutados ---");
    }
}
