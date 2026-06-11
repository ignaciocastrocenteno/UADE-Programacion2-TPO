package implementacion;

import especificacion.PilaTDA;

public class PilaDin implements PilaTDA {
	private class Nodo { //la célula de la estructura
		int info; //el valor almacenado
		Nodo sig; //la referencia al siguiente nodo
	}
	
	private Nodo primero; //la referencia a la estructura
	
	public void inicializarPila() {
		primero = null;
	}
	
	public void apilar(int x) { //el nuevo elemento se agrega al inicio
		Nodo nuevo = new Nodo();
		nuevo.info = x;
		nuevo.sig = primero;
		primero = nuevo; //nueva referencia a la estructura
	}
	
	public void desapilar() {
		primero = primero.sig; //nueva referencia a la estructura
	}
	
	public boolean pilaVacia() {
		return (primero == null);
	}
	
	public int tope() {
		return primero.info;
	}
}