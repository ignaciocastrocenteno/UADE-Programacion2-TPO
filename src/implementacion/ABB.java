package implementacion;

import especificacion.ABBTDA;

public class ABB implements ABBTDA {
	private class NodoABB {
		int info;
		ABBTDA hI;
		ABBTDA hD;
	} 
	private NodoABB a;

	public void inicializarArbol(){
		a = null;
	}

	public int raiz(){
		return a.info;
	}

	public boolean arbolVacio(){
		return (a == null);
	}

	public ABBTDA hijoDer(){
		return a.hD;
	}

	public ABBTDA hijoIzq(){
		return a.hI;
	}

	private int mayor(ABBTDA a){
		if (a.hijoDer().arbolVacio())
			return a.raiz(); //llegamos
		else
			return mayor(a.hijoDer()); //no llegamos todavía
	}

	private int menor(ABBTDA a){
		if (a.hijoIzq().arbolVacio())
			return a.raiz(); //llegamos
		else
			return menor(a.hijoIzq()); //no llegamos todavía
	}

	 
	public void agregarElem(int x){
		if (a == null) { //caso de árbol vacío
			a = new NodoABB();
			a.info = x;
			a.hI = new ABB();
			a.hI.inicializarArbol();
			a.hD = new ABB();
			a.hD.inicializarArbol();
		} else if (a.info > x) //caso de árbol izquierdo
			a.hI.agregarElem(x);
		else if (a.info < x) //caso de árbol derecho
			a.hD.agregarElem(x);
		else //lo encontré a x
			;//no tengo que hacer nada
	}

	public void eliminarElem(int x){
		if (a != null) { //verificación de árbol no vacío
			if (a.info == x && a.hI.arbolVacio() && a.hD.arbolVacio()) { //es una hoja
				a = null;
			} else if (a.info == x && !a.hI.arbolVacio()) {
				int mayor = mayor(a.hI);
				a.info = mayor; //reemplazamos con el mayor de los menores
				a.hI.eliminarElem(mayor);
			} else if (a.info == x && a.hI.arbolVacio()) { //!a.hD.arbolVacio()
				int menor = menor(a.hD);
				a.info = menor; //reemplazamos con el menor de los mayores
				a.hD.eliminarElem(menor);
			} else if (a.info < x) { //seguimos buscando por los mayores (derecha)
				a.hD.eliminarElem(x);
			} else { //a.info > x
				a.hI.eliminarElem(x); //seguimos buscando por los menores (izquierda)
			}
		} else //no lo encontré a x
			; //no tengo que hacer nada
	}
} 
