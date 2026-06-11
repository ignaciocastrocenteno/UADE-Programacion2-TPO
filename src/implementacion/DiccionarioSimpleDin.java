package implementacion;

import especificacion.ConjuntoTDA;
import especificacion.DiccionarioSimpleTDA;

public class DiccionarioSimpleDin implements DiccionarioSimpleTDA {
	private class NodoClave { //la célula de la estructura de claves
		int clave; //la clave
		int valor; //el valor
		NodoClave sigClave; //la referencia a la siguiente clave
	}
	
	private NodoClave origen; //la referencia de la estructura
	
	public void inicializarDiccionario() {
		origen = null;
	}
	
	public void agregar(int clave, int valor) {
		NodoClave nc = Clave2NodoClave(clave);
		if (nc == null) { //la clave no existe
			nc = new NodoClave();
			nc.clave = clave;
			nc.sigClave = origen;
			origen = nc; //nuevo origen
		}
		nc.valor = valor;
	}
	private NodoClave Clave2NodoClave(int clave){
		NodoClave aux = origen; //el nodo viajero
		while (aux != null && aux.clave != clave) {
			aux = aux.sigClave;
		}
		return aux;
	}
	public void eliminar(int clave) {
		if (origen != null) {
			if (origen.clave == clave) { //es el primero
				origen = origen.sigClave;
			}
			else { //es algún otro
				NodoClave aux = origen; //el nodo viajero
				while (aux.sigClave != null && aux.sigClave.clave != clave){
					aux = aux.sigClave;
				}
				if (aux.sigClave != null) {
					aux.sigClave = aux.sigClave.sigClave;
				}
			}
		}
	}
	public int recuperar(int clave) {
		NodoClave nc = Clave2NodoClave(clave);
		return nc.valor;
	}
	public ConjuntoTDA claves() {
		ConjuntoTDA c = new Conjunto();
		c.inicializarConjunto();
		NodoClave aux = origen; //el nodo viajero
		while (aux != null) {
			c.agregar(aux.clave);
			aux = aux.sigClave;
		}
		return c;
	}
}