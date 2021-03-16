package domain;
import java.awt.Color;


public class CelulaNormal extends Celula 
{

    /**
     * Constructor for objects of class CelulaNormal
     */
    public CelulaNormal(AutomataCelular ac,int fila, int columna)
    {
        super(ac,fila,columna);
        color=Color.blue;
    }
    
    /**Decide cual va a ser su  siguiente estado 
     */
    public void decida(Elemento [] vecinos){
        if (getEdad()>=3){
            estadoSiguiente=Ser.MUERTO;
        }
    }
}
