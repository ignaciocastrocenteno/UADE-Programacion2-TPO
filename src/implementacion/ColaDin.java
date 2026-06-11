package implementacion;

import especificacion.ColaTDA;

public class ColaDin implements ColaTDA {
	private class Nodo { //la célula de la estructura
		int info; //el valor almacenado
		Nodo sig; //la referencia al siguiente nodo
	}

	private Nodo primero; //primer elemento (más antiguo)
	private Nodo ultimo; //último elemento (más reciente)
	
	public void inicializarCola(){
		primero = null;
		ultimo = null;
	}
	
	public void acolar(int x){
		Nodo nuevo = new Nodo();
		nuevo.info = x;
		nuevo.sig = null;
		if (ultimo != null) //cola no vacía
			ultimo.sig = nuevo;
		ultimo = nuevo;
		if (primero == null) //la cola estaba vacía
			primero = nuevo;
	}
	
	public void desacolar(){
		primero = primero.sig; //nueva referencia a la estructura
		if (primero == null) //la cola quedó vacía
			ultimo = null;
	}
	
	public boolean colaVacia(){
		return (ultimo == null);
	}
	
	public int primero(){
		return primero.info;
	}
}
