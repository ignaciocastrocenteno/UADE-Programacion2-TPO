package implementacion;

import especificacion.ColaTDA;

public class Cola implements ColaTDA {
    private int[] arr;
    private int indice;

    public Cola() {
    }

    public void inicializarCola() {
        this.arr = new int[100];
        this.indice = 0;
    }

    public void acolar(int x) {
        for(int i = this.indice - 1; i >= 0; --i) {
            this.arr[i + 1] = this.arr[i];
        }

        this.arr[0] = x;
        ++this.indice;
    }

    public void desacolar() {
        --this.indice;
    }

    public int primero() {
        return this.arr[this.indice - 1];
    }

    public boolean colaVacia() {
        return this.indice == 0;
    }
}
