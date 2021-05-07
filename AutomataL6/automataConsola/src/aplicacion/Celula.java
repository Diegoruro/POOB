package aplicacion;

import java.awt.Color;

/**Informacion sobre una célula<br>
<b>(automata,fila,columna,edad, estado, estadoSigiente, color)</b><br>
Las celulas conocen el automata en la que viven, la posición en la que están en ese autómata,su edad, su 
estado actual y el estado que van a tomar en el siguiente instante.<br>
Todas las células son de color azul<br>
 */
public class Celula extends Ser implements Elemento{
    public char estadoSiguiente;
    public Color color;
    public AutomataCelular automata;
    public int fila,columna;
    private Elemento[] vecinos;
    private Elemento[][] matrix;
    private int vecinosVivos;


    /**Crea una célula en la posición (<b>fila,columna</b>) del autómta <b>ac</b>.Toda nueva célula va a estar viva en el estado siguiente.
    *@param ac automata celular en el que se va a ubicar la nueva célula
    *@param fila fila en el automata celular
    *@param columna columna en el automata celula
    */
    public Celula(AutomataCelular ac,int fila, int columna){
        automata=ac;
        this.fila=fila;
        this.columna=columna;
        estadoSiguiente=Ser.VIVO;
        automata.setElemento(fila,columna,(Elemento)this);
        automata.poblacion++;
    }

    /**Retorna la fila del automata en que se encuentra 
    @return 
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
    public void decida(Elemento [] vecinos){
    }

    
    /**
    @see Elemento.cambie()
     */
    public final void cambie(){
        cumple();
        estado=estadoSiguiente;
    }
}
