package especificacion;

public interface MultiPilaTDA { 
    public void apilar (PilaTDA valores); 
    public void desapilar (PilaTDA valores); 
    public PilaTDA tope (int cantidad); 
    public void inicializarPila(); 
    public boolean pilaVacia(); 
}
