package domain;

import java.awt.Color;
import java.io.Serializable;

/**Informacion sobre una célula<br>
<b>(automata,fila,columna,edad, estado, estadoSigiente, color)</b><br>
Las celulas conocen el automata en la que viven, la posición en la que están en ese autómata,su edad, su 
estado actual y el estado que van a tomar en el siguiente instante.<br>
Todas las células son de color azul<br>
 */
public class Celula extends Ser implements Elemento, Serializable {
    protected char estadoSiguiente;
    protected Color color;
    protected AutomataCelular automata;
    protected int fila,columna;
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

    
    /**Retorna el color de  la célula
    @return 
     */
    public final Color getColor(){
        return color;
    }    
    

    /**Decide cual va a ser su  siguiente estado 
    @param Elemento [] vecinos lista de los vecinos que rodean al elemento
     */
    public void decida(Elemento [] vecinos){
    }

    
    /**Actualiza su estado actual considerando lo definido como siguiente estado
     */
    public final void cambie(){
        cumple();
        estado=estadoSiguiente;
    }

    public void setEstadoSiguiente(char estadoSiguiente) {
        this.estadoSiguiente = estadoSiguiente;
    }
}