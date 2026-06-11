package implementacion;

import especificacion.ConjuntoTDA;

public class ConjuntoDin implements ConjuntoTDA {
	private class Nodo { //la célula de la estructura
		int info; //el valor almacenado
		Nodo sig; //la referencia al siguiente nodo
	}
	
	private Nodo c; //la referencia de la estructura
	
	public void inicializarConjunto () {
		c = null;
	}
	
	public boolean conjuntoVacio() {
		return (c == null);
	}
	
	public void agregar(int x) {
		if (!this.pertenece(x)) { //se verifica pertenencia
			Nodo nuevo = new Nodo(); //el nuevo nodo que se agregará
			nuevo.info = x;
			nuevo.sig = c;
			c = nuevo;
		}
	}
	
	public int elegir() { //arbitrario
		return c.info; //elegimos el primero (puede ser cualquiera)
	}
	
	public void sacar(int x) {
		if (c != null) {
			if (c.info == x) { //es el primero
				c = c.sig;
			} else { //es algún otro; lo buscamos
				Nodo aux = c;
				while (aux.sig != null && aux.sig.info != x)
					aux = aux.sig;
				if (aux.sig != null) //encontrado
					aux.sig = aux.sig.sig;
			}
		}
	}
	
	public boolean pertenece(int x) {
		Nodo aux = c;
		while (aux != null && aux.info != x)
			aux = aux.sig;
		return (aux != null);
	}
}