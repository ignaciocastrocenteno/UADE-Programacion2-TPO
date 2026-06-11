package implementacion;

import especificacion.ConjuntoTDA;
import especificacion.DiccionarioMultipleTDA;

public class DiccionarioMultiple implements DiccionarioMultipleTDA {
    private DiccionarioMultiple.Elemento[] elementos;
    private int cantClaves;

    public DiccionarioMultiple() {
    }

    public void inicializarDiccionario() {
        this.elementos = new DiccionarioMultiple.Elemento[100];
        this.cantClaves = 0;
    }

    public void agregar(int clave, int valor) {
        int posC = this.clave2Indice(clave);
        if (posC == -1) {
            this.elementos[this.cantClaves] = new DiccionarioMultiple.Elemento();
            this.elementos[this.cantClaves].clave = clave;
            this.elementos[this.cantClaves].valores = new int[100];
            this.elementos[this.cantClaves].valores[0] = valor;
            this.elementos[this.cantClaves].cantValores = 1;
            ++this.cantClaves;
        } else {
            DiccionarioMultiple.Elemento e = this.elementos[posC];
            int posV = this.valor2Indice(e, valor);
            if (posV == -1) {
                e.valores[e.cantValores] = valor;
                ++e.cantValores;
            }
        }

    }

    private int clave2Indice(int clave) {
        int i;
        for(i = this.cantClaves - 1; i >= 0 && this.elementos[i].clave != clave; --i) {
        }

        return i;
    }

    private int valor2Indice(DiccionarioMultiple.Elemento e, int valor) {
        int i;
        for(i = e.cantValores - 1; i >= 0 && e.valores[i] != valor; --i) {
        }

        return i;
    }

    public void eliminar(int clave) {
        int pos = this.clave2Indice(clave);
        if (pos != -1) {
            this.elementos[pos] = this.elementos[this.cantClaves - 1];
            --this.cantClaves;
        }

    }

    public void eliminarValor(int clave, int valor) {
        int posC = this.clave2Indice(clave);
        if (posC != -1) {
            DiccionarioMultiple.Elemento e = this.elementos[posC];
            int posV = this.valor2Indice(e, valor);
            if (posV != -1) {
                e.valores[posV] = e.valores[e.cantValores - 1];
                --e.cantValores;
                if (e.cantValores == 0) {
                    this.eliminar(clave);
                }
            }
        }

    }

    public ConjuntoTDA recuperar(int clave) {
        ConjuntoTDA c = new Conjunto();
        c.inicializarConjunto();
        int pos = this.clave2Indice(clave);
        if (pos != -1) {
            DiccionarioMultiple.Elemento e = this.elementos[pos];

            for(int i = 0; i < e.cantValores; ++i) {
                c.agregar(e.valores[i]);
            }
        }

        return c;
    }

    public ConjuntoTDA claves() {
        ConjuntoTDA c = new Conjunto();
        c.inicializarConjunto();

        for(int i = 0; i < this.cantClaves; ++i) {
            c.agregar(this.elementos[i].clave);
        }

        return c;
    }

    class Elemento {
        int clave;
        int[] valores;
        int cantValores;

        Elemento() {
        }
    }
}
