package domain;

import java.awt.Color;

/**Informacion sobre una célula<br>
<b>(automata,fila,columna,edad, estado, estadoSigiente, color)</b><br>
Las celulas conocen el automata en la que viven, la posición en la que están en ese autómata,su edad, su 
estado actual y el estado que van a tomar en el siguiente instante.<br>
Todas las células son de color azul<br>
 */
public class Celula extends Ser implements Elemento{
    protected char estadoSiguiente;
    protected Color color;
    private AutomataCelular automata;
    private int fila,columna;
    private Elemento[] vecinos;
    private Elemento[][] matrix;
    private int vecinosVivos;


    /**Crea una célula en la posición (<b>fila,columna</b>) del autómta <b>ac</b>.Toda nueva célula va a estar viva en el estado siguiente.
    @param ac automata celular en el que se va a ubicar la nueva célula
    @param fila fila en el automata celular
    @param columna columna en el automata celula
     */
    public Celula(AutomataCelular ac,int fila, int columna){
        automata=ac;
        this.fila=fila;
        this.columna=columna;
        estadoSiguiente=Ser.VIVO;
        automata.setElemento(fila,columna,(Elemento)this);  
        color=Color.blue;
        matrix = this.automata.getMatrix();
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
    
    public int getVecinosVivos(){
        return this.vecinosVivos;
    }
    
    public Elemento[] vecinos()
    {
        vecinos = new Elemento[8];
        int cont = 0;
        for (int i=this.fila-1;i<this.fila+2;i++){
            for (int j=this.columna-1;j<this.columna+2;j++){
                if (i!=this.fila || j!=this.columna ){
                    vecinos[cont] = this.automata.getElemento(i, j);
                    cont++;
                }
            }
        }

        return vecinos;
    }

    /**Decide cual va a ser su  siguiente estado 
     */
    public void decida(){
        if (getEdad()>=3){
            estadoSiguiente=Ser.MUERTO;
        }else if(getVecinosVivos() == 3 && !this.isVivo()){
            estadoSiguiente=Ser.VIVO;
        }else if((getVecinosVivos() == 2 || getVecinosVivos() == 3) && this.isVivo()){
            estadoSiguiente=Ser.VIVO;
        }else if(getVecinosVivos()<2 || getVecinosVivos()>3){
            estadoSiguiente=Ser.MUERTO;
        }else if (!this.isVivo()){
        }
    }

    private void calculateVecinosVivos(){
        vecinosVivos = 0;
        for (Elemento vecino: this.vecinos){
            if (vecino.isVivo()){
                vecinosVivos += 1;
            }
        }
    }

    
    /**Actualiza su estado actual considerando lo definido como siguiente estado
     */
    public final void cambie(){
        cumple();
        estado=estadoSiguiente;
    }
}
