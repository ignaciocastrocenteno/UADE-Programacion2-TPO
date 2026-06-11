package implementacion;

import especificacion.ConjuntoTDA;
import especificacion.GrafoTDA;

public class GrafoDin implements GrafoTDA {
	private class NodoVertice{
		int vertice;
		NodoArista aristas;
		NodoVertice sigVertice;
	}

	private class NodoArista{
		int peso;
		NodoVertice verticeDestino;
		NodoArista sigArista;
	}
	
	private NodoVertice origen;
	
	public void inicializarGrafo() {
		origen = null;
	}
	
	public void agregarVertice(int v) { //El vÃ©rtice se inserta al inicio de la lista de nodos
		NodoVertice aux = new NodoVertice();
		aux.vertice = v;
		aux.aristas = null ;
		aux.sigVertice = origen;
		origen = aux;
	}
	
	public void agregarArista(int v1, int v2, int peso ) {
		NodoVertice n1 = vert2Nodo(v1); //Buscamos el nodo origen...
		NodoVertice n2 = vert2Nodo(v2); //... y el nodo destino
		NodoArista aux = new NodoArista(); //La arista va al inicio de la lista...
		aux.peso = peso; //... de aristas salientes de v1
		aux.verticeDestino = n2;
		aux.sigArista = n1.aristas;
		n1.aristas = aux;
	}
	
	private NodoVertice vert2Nodo(int v) { //Dado un valor, busca el nodo correspondiente
		NodoVertice aux = origen;
		while (aux != null && aux.vertice != v)
			aux = aux.sigVertice;
		return aux;
	}
	
	public void eliminarVertice(int v) {
		if (origen.vertice == v) //Es el origen
			origen = origen.sigVertice; //Se elimina el origen
		NodoVertice aux = origen; //No es el origen; hay que buscarlo
		while (aux != null) { //Eliminamos aristas hacia v
			this.eliminarAristaNodo(aux, v);
			if (aux.sigVertice != null && aux.sigVertice.vertice == v)
				aux.sigVertice = aux.sigVertice.sigVertice; //Si es el nodo, lo elimina
			aux = aux.sigVertice; //Sigue eliminando aristas
		}
	}

	private void eliminarAristaNodo(NodoVertice nodo, int v) {
		NodoArista aux = nodo.aristas; //Elimina de nodo las aristas hacia v
		if (aux != null) {
			if (aux.verticeDestino.vertice == v) { //Hay que eliminar la primera arista
				nodo.aristas = aux.sigArista;
			} else { //No es la primera; la buscamos
				while (aux.sigArista != null && aux.sigArista.verticeDestino.vertice != v)
					aux = aux.sigArista;
				if (aux.sigArista != null) { //Eliminamos la arista
					aux.sigArista = aux.sigArista.sigArista;
				}
			}
		}
	}
	
	public ConjuntoTDA vertices() {
		ConjuntoTDA c = new Conjunto();
		c.inicializarConjunto();
		NodoVertice aux = origen;
		while (aux != null) {
			c.agregar(aux.vertice);
			aux = aux.sigVertice;
		}
		return c;
	}
	
	public void eliminarArista(int v1, int v2) {
		NodoVertice n1 = vert2Nodo(v1);
		eliminarAristaNodo(n1, v2);
	}
	
	public boolean existeArista(int v1, int v2) {
		NodoVertice n1 = vert2Nodo(v1);
		NodoArista aux = n1.aristas;
		while (aux != null && aux.verticeDestino.vertice != v2) {
			aux = aux.sigArista;
		}
		//Solo si se encontro la arista buscada, aux no es null
		return aux != null;
	}
	
	public int pesoArista(int v1, int v2) {
		NodoVertice n1 = vert2Nodo(v1);
		NodoArista aux = n1.aristas;
		while (aux.verticeDestino.vertice != v2)
			aux = aux.sigArista; //Buscamos la arista
		return aux.peso;
	}
}