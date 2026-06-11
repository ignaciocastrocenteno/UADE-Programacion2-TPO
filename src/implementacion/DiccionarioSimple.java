package implementacion;

import especificacion.ConjuntoTDA;
import especificacion.DiccionarioSimpleTDA;

public class DiccionarioSimple implements DiccionarioSimpleTDA {
    private DiccionarioSimple.Elemento[] elementos;
    private int cant;

    public DiccionarioSimple() {
    }

    public void inicializarDiccionario() {
        this.cant = 0;
        this.elementos = new DiccionarioSimple.Elemento[100];
    }

    public void agregar(int clave, int valor) {
        int pos = this.clave2Indice(clave);
        if (pos == -1) {
            this.elementos[this.cant] = new DiccionarioSimple.Elemento();
            this.elementos[this.cant].clave = clave;
            this.elementos[this.cant].valor = valor;
            ++this.cant;
        } else {
            this.elementos[pos].valor = valor;
        }

    }

    private int clave2Indice(int clave) {
        int i;
        for(i = this.cant - 1; i >= 0 && this.elementos[i].clave != clave; --i) {
        }

        return i;
    }

    public void eliminar(int clave) {
        int pos = this.clave2Indice(clave);
        if (pos != -1) {
            this.elementos[pos] = this.elementos[this.cant - 1];
            --this.cant;
        }

    }

    public int recuperar(int clave) {
        int pos = this.clave2Indice(clave);
        return this.elementos[pos].valor;
    }

    public ConjuntoTDA claves() {
        ConjuntoTDA c = new Conjunto();
        c.inicializarConjunto();

        for(int i = 0; i < this.cant; ++i) {
            c.agregar(this.elementos[i].clave);
        }

        return c;
    }

    private class Elemento {
        int clave;
        int valor;

        private Elemento() {
        }
    }
}
