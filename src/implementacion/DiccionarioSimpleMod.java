package implementacion;

import especificacion.DiccionarioSimpleModTDA;
import especificacion.DiccionarioSimpleTDA;
import especificacion.ConjuntoTDA;

public class DiccionarioSimpleMod implements DiccionarioSimpleModTDA {
    private DiccionarioSimpleTDA dValores;
    private DiccionarioSimpleTDA dMods;

    public void inicializarDiccionario() {
        dValores = new DiccionarioSimple(); // C
        dValores.inicializarDiccionario(); // C
        dMods = new DiccionarioSimple(); // C
        dMods.inicializarDiccionario(); // C
    }

    public void agregar(int clave, int valor) {
        ConjuntoTDA claves = dValores.claves(); // C
        // Chequeamos si la clave ya esta registrada en nuestro diccionario interno
        if (claves.pertenece(clave)) { // L
            int valorActual = dValores.recuperar(clave); // L
            // Solo aumentamos el factor de modificacion si el valor a insertar difiere del que ya teniamos
            if (valorActual != valor) { // C
                int mods = dMods.recuperar(clave); // L
                // Actualizamos ambos diccionarios borrando e insertando (para garantizar la actualizacion en implementaciones cerradas)
                dMods.eliminar(clave); // L
                dMods.agregar(clave, mods + 1); // L
                dValores.eliminar(clave); // L
                dValores.agregar(clave, valor); // L
            }
        } else {
            // Si la clave es totalmente nueva, se agrega con su valor asociado y arranca con 0 modificaciones
            dValores.agregar(clave, valor); // C
            dMods.agregar(clave, 0); // C
        }
    }

    public void eliminar(int clave) {
        dValores.eliminar(clave); // L
        dMods.eliminar(clave); // L
    }

    public int recuperar(int clave) {
        return dValores.recuperar(clave); // L
    }

    public int recuperarMod(int clave) {
        return dMods.recuperar(clave); // L
    }

    public ConjuntoTDA claves() {
        return dValores.claves(); // C
    }
}
