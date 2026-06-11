package implementacion;

import especificacion.ConjuntoTDA;
import especificacion.GrafoTDA;

public class Grafo implements GrafoTDA {
    private int[][] mAdy;
    private int[] etiqs;
    private int cantNodos;

    public Grafo() {
    }

    public void inicializarGrafo() {
        this.mAdy = new int[100][100];
        this.etiqs = new int[100];
        this.cantNodos = 0;
    }

    public void agregarVertice(int v) {
        this.etiqs[this.cantNodos] = v;

        for(int i = 0; i <= this.cantNodos; ++i) {
            this.mAdy[this.cantNodos][i] = 0;
            this.mAdy[i][this.cantNodos] = 0;
        }

        ++this.cantNodos;
    }

    public void eliminarVertice(int v) {
        int ind = this.vert2Indice(v);

        for(int k = 0; k < this.cantNodos; ++k) {
            this.mAdy[k][ind] = this.mAdy[k][this.cantNodos - 1];
        }

        for(int k = 0; k < this.cantNodos; ++k) {
            this.mAdy[ind][k] = this.mAdy[this.cantNodos - 1][k];
        }

        this.etiqs[ind] = this.etiqs[this.cantNodos - 1];
        --this.cantNodos;
    }

    private int vert2Indice(int v) {
        int i;
        for(i = this.cantNodos - 1; i >= 0 && this.etiqs[i] != v; --i) {
        }

        return i;
    }

    public ConjuntoTDA vertices() {
        ConjuntoTDA vert = new Conjunto();
        vert.inicializarConjunto();

        for(int i = 0; i < this.cantNodos; ++i) {
            vert.agregar(this.etiqs[i]);
        }

        return vert;
    }

    public void agregarArista(int v1, int v2, int peso) {
        int o = this.vert2Indice(v1);
        int d = this.vert2Indice(v2);
        this.mAdy[o][d] = peso;
    }

    public void eliminarArista(int v1, int v2) {
        int o = this.vert2Indice(v1);
        int d = this.vert2Indice(v2);
        this.mAdy[o][d] = 0;
    }

    public boolean existeArista(int v1, int v2) {
        int o = this.vert2Indice(v1);
        int d = this.vert2Indice(v2);
        return this.mAdy[o][d] != 0;
    }

    public int pesoArista(int v1, int v2) {
        int o = this.vert2Indice(v1);
        int d = this.vert2Indice(v2);
        return this.mAdy[o][d];
    }
}
