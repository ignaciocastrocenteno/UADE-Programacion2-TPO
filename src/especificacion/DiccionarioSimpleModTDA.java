package especificacion;

public interface DiccionarioSimpleModTDA { 
    public void inicializarDiccionario(); 
    public void agregar(int clave, int valor); 
    public void eliminar(int clave); 
    public int recuperar(int clave); 
    public int recuperarMod(int clave); 
    public ConjuntoTDA claves(); 
}
