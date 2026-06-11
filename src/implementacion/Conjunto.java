package implementacion;

import especificacion.ConjuntoTDA;

public class Conjunto implements ConjuntoTDA {
    private int[] a;
    private int cant;

    public Conjunto() {
    }

    public void inicializarConjunto() {
        this.a = new int[100];
        this.cant = 0;
    }

    public void agregar(int x) {
        if (!this.pertenece(x)) {
            this.a[this.cant] = x;
            ++this.cant;
        }

    }

    public boolean conjuntoVacio() {
        return this.cant == 0;
    }

    public int elegir() {
        int max = this.cant - 1;
        int min = 0;
        int pos = (int)(Math.random() * (double)(max - min + 1) + (double)min);
        return this.a[pos];
    }

    public boolean pertenece(int x) {
        int i;
        for(i = 0; i < this.cant && this.a[i] != x; ++i) {
        }

        return i < this.cant;
    }

    public void sacar(int x) {
        int i;
        for(i = 0; i < this.cant && this.a[i] != x; ++i) {
        }

        if (i < this.cant) {
            this.a[i] = this.a[this.cant - 1];
            --this.cant;
        }

    }
}
