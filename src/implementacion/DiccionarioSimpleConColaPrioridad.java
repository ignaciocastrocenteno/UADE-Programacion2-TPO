package implementacion;

import especificacion.ConjuntoTDA;
import especificacion.DiccionarioSimpleTDA;
import especificacion.ColaPrioridadTDA;

public class DiccionarioSimple implements DiccionarioSimpleTDA { 
    // ahorro de redundancias
    private ColaPrioridad colaPrioridad;  //c
    // inicializo cola
    public void inicializarDiccionario() {  //c
        colaPrioridad = new ColaPrioridad(); //c
        colaPrioridad.inicializarCola();    //c
    }
    //uso eliminar que se fija en todos los valores si es que existe la clave anteriormente en la cola y la elimino y la vuelvo a ingresar
    public void agregar(int clave, int valor) {//p
        eliminar(clave); //p                               
        colaPrioridad.acolarPrioridad(clave, valor); //l    
    }
    // recorro cola con auxiliar y no acolo si es que la clave coincide con el valor a eliminar
    public void eliminar(int clave) {//p
        ColaPrioridad aux = new ColaPrioridad(); //c
        aux.inicializarCola(); //c

        while (!colaPrioridad.colaVacia()) {//p
            if (clave != colaPrioridad.primero()) {//c
                aux.acolarPrioridad(colaPrioridad.primero(), colaPrioridad.prioridad());//l
            }
            colaPrioridad.desacolar(); //c                                      
        }
        while (!aux.colaVacia()) {//p
            colaPrioridad.acolarPrioridad(aux.primero(), aux.prioridad());  //l
            aux.desacolar(); //c    
        }
    }
    // misma logica que en eliminar se recorre toda la cola hasta encontrar clave que coincida y guardar su valor/prioridad
    public int recuperar(int clave) {//p
        ColaPrioridad aux = new ColaPrioridad();//c
        aux.inicializarCola();//c
        int valor = 0;//c

        while (!colaPrioridad.colaVacia()) {//p
            if (clave == colaPrioridad.primero()) {//c   
                valor = colaPrioridad.prioridad();//c
            }
            aux.acolarPrioridad(colaPrioridad.primero(), colaPrioridad.prioridad());//l
            colaPrioridad.desacolar();//c                  
        }
        while (!aux.colaVacia()) {//p
            colaPrioridad.acolarPrioridad(aux.primero(), aux.prioridad());//l
            aux.desacolar();//c
        }
        return valor;//c                                 
    }
    //se recorre guardando los valores en un auxiliar para luego reingresarlos en la cola original y a su vez guardando las claves en un conjunto
    public ConjuntoTDA claves() {//p
        Conjunto claves = new Conjunto(); //c
        claves.inicializarConjunto();//c
        ColaPrioridad aux = new ColaPrioridad();//c
        aux.inicializarCola();//c

        while (!colaPrioridad.colaVacia()) {//p
            claves.agregar(colaPrioridad.primero());//l
            aux.acolarPrioridad(colaPrioridad.primero(), colaPrioridad.prioridad()); //l
            colaPrioridad.desacolar();//c              
        }
        while (!aux.colaVacia()) {//p
            colaPrioridad.acolarPrioridad(aux.primero(), aux.prioridad());//l
            aux.desacolar();//c                         
        }
        return claves;//c                               
    }
}