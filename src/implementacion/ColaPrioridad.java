package implementacion;

import especificacion.ColaPrioridadTDA;

public class ColaPrioridad implements ColaPrioridadTDA {
    private ColaPrioridad.Elemento[] elementos;
    private int indice;

    public ColaPrioridad() {
    }

    public void inicializarCola() {
        this.indice = 0;
        this.elementos = new ColaPrioridad.Elemento[100];
    }

    public void acolarPrioridad(int x, int prio) {
        int j;
        for(j = this.indice; j > 0 && this.elementos[j - 1].prioridad >= prio; --j) {
            this.elementos[j] = this.elementos[j - 1];
        }

        this.elementos[j] = new ColaPrioridad.Elemento();
        this.elementos[j].valor = x;
        this.elementos[j].prioridad = prio;
        ++this.indice;
    }

    public void desacolar() {
        --this.indice;
    }

    public int primero() {
        return this.elementos[this.indice - 1].valor;
    }

    public int prioridad() {
        return this.elementos[this.indice - 1].prioridad;
    }

    public boolean colaVacia() {
        return this.indice == 0;
    }

    private class Elemento {
        int valor;
        int prioridad;

        private Elemento() {
        }
    }
}
