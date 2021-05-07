package aplicacion;
import java.awt.Color;


public class Poblacion implements Elemento
{
    public Color color;
    public AutomataCelular automata;
    public int fila,columna;
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

    /**
    @see Elemento.getColor()
     */
    public final Color getColor(){
        return color;
    }    
    
    /**
    @see Elemento.decida()
     */
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
