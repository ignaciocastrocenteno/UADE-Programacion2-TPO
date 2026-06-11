package implementacion;

import especificacion.ColaPrioridadTDA;

public class ColaPrioridadDin implements ColaPrioridadTDA {
	private class NodoPrioridad { //la célula de la estructura
		int info; //el valor almacenado
		int prioridad; //la prioridad
		NodoPrioridad sig; //la referencia al siguiente nodo
	}
	
	private NodoPrioridad mayorPrioridad; //primer elemento (prioritario)
	
	public void inicializarCola() {
		mayorPrioridad = null;
	}
	
	public void acolarPrioridad(int x, int prioridad) {
		NodoPrioridad nuevo = new NodoPrioridad(); //el nuevo nodo que se acolará
		nuevo.info = x;
		nuevo.prioridad = prioridad;
		if (mayorPrioridad == null || prioridad > mayorPrioridad.prioridad) {
			nuevo.sig = mayorPrioridad; //el nodo debe ir al principio
			mayorPrioridad = nuevo;
		}
		else {
			NodoPrioridad aux = mayorPrioridad; //buscamos la posición
			while(aux.sig != null && aux.sig.prioridad >= prioridad){
				aux = aux.sig;
			}
			nuevo.sig = aux.sig;
			aux.sig = nuevo;
		}
	}
	
	public void desacolar() {
		mayorPrioridad = mayorPrioridad.sig; //nueva referencia a la estructura
	}
	
	public int primero() {
		return mayorPrioridad.info ;
	}
	
	public boolean colaVacia() {
		return (mayorPrioridad == null);
	}
	
	public int prioridad() {
		return mayorPrioridad.prioridad;
	}
}