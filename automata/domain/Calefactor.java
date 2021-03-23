package domain;
import java.awt.Color;

/**
 * Comportamientos de un calefactor.
 * 
 * @author (Juan Aguas - Diego Ruiz) 
 * @version (1)
 */
public class Calefactor implements Elemento
{
    protected Color color;
    protected AutomataCelular automata;
    protected int fila,columna;
    /**
     * Constructor for objects of class Calefactor
     */
    public Calefactor(AutomataCelular ac,int fila, int columna)
    {
        automata=ac;
        this.fila=fila;
        this.columna=columna;
        automata.setElemento(fila,columna,(Elemento)this); 
        this.color=Color.red;
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
        if (this.color==Color.red){
            this.color=Color.yellow;
        }
        else{
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
