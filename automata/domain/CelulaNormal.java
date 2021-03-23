package domain;
import java.awt.Color;


/**
 * Comportamientos de una célula normal.
 * 
 * @author (Juan Aguas - Diego Ruiz) 
 * @version (1)
 */
public class CelulaNormal extends Celula 
{

    /**
     */
    public CelulaNormal(AutomataCelular ac,int fila, int columna)
    {
        super(ac,fila,columna);
        color=Color.blue;
    }
    
    /**
    @see Elemento.decida()
     */
    public void decida(Elemento [] vecinos){
        if (getEdad()>=3){
            estadoSiguiente=Ser.MUERTO;
        }
    }
}
