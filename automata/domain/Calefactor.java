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
    public Color color;
    public AutomataCelular automata;
    public int fila,columna;
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

    /**
    @see Elemento.getColor()
     */
    public final Color getColor(){
        return color;
    }    
    
    /**
    @see Elemento.cambie()
     */
    public void cambie(){
        if (this.color==Color.red){
            this.color=Color.yellow;
        }
        else{
            this.color=Color.red;
        }
    }
    
    /**
    @see Elemento.forma()
     */
    public int forma(){
      return 2;
    }
    
    /**
    @see Elemento.isVivo()
     */
    public boolean isVivo(){
      return true;
  }
}
