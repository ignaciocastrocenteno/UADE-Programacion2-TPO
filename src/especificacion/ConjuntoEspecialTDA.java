package especificacion;

public interface ConjuntoEspecialTDA { 
    public class Respuesta { 
        public boolean error; 
        public int rta; 
    } 
    public void inicializarConjunto(); 
    public Respuesta agregar(int valor); 
    public Respuesta sacar(int valor); 
    public Respuesta elegir(); 
    public boolean pertenece(int valor); 
    public boolean conjuntoVacio(); 
}
