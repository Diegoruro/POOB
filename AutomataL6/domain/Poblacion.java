package domain;
import java.awt.Color;

/**
 * Comportamientos del elemento población.
 * 
 * @author (Juan Aguas - Diego Ruiz) 
 * @version (1)
 */
public class Poblacion implements Elemento
{
    protected Color color;
    protected AutomataCelular automata;
    protected int fila,columna;
    /**
     * Constructor for objects of class poblacion
     */
    public Poblacion(AutomataCelular ac,int fila, int columna)
    {
        automata=ac;
        this.fila=fila;
        this.columna=columna;
        automata.setElemento(fila,columna,(Elemento)this);
        this.color=Color.white;
    }

    
    /**
     * Retorna la fila del automata en que se encuentra 
     * @return 
     */
    public final int getFila(){
        return fila;
    }

    /**Retorna la columna del automata en que se encuentra
    @return 
     */
    public final int getColumma(){
        return columna;
    }

    /**Retorna el color del calefactor
    @return 
     */
    public final Color getColor(){
        return color;
    }    
    
    
    public void cambie(){
        int n=automata.poblacion;
        if (n>0 && n<30){
            this.color=Color.green;
        }
        else if (n>=30 && n<60){
            this.color=Color.orange;
        }
        else if (n>=60){
            this.color=Color.red;
        }
    }
    
    
    public int forma(){
      return 2;
    }
    
    public boolean isVivo(){
      return true;
    }
}
