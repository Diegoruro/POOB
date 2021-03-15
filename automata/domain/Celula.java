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
        automata.vecinos(this.fila, this.columna);
        return this.vecinosVivos;
    }

    /**Decide cual va a ser su  siguiente estado 
     */
    public void decida(){
        if (getEdad()>=3){
            estadoSiguiente=Ser.MUERTO;
        }
        if(getVecinosVivos() == 3 && !this.isVivo()){
            estadoSiguiente=Ser.VIVO;
        }
        if((getVecinosVivos() == 2 || getVecinosVivos() == 3) && this.isVivo()){
            estadoSiguiente=Ser.VIVO;
        }
        if(getVecinosVivos()<2 || getVecinosVivos()>3){
            estadoSiguiente=Ser.MUERTO;
        }
        int cont = 1;
        for (int i = 0, i<vecinos.lenght;){
            int vivos = 0;
            if (vecino == null ){
                    switch(cont){
                        case 1:
                            if (automata.vecinos(this.fila-1, this.columna-1)==3){
                                new Celula(automata, this.fila-1, this.columna-1);
                            }
                        case 2:
                            if (automata.vecinos(this.fila-1, this.columna)==3){
                                new Celula(automata, this.fila-1, this.columna-1);
                            }
                        case 3:
                            if (automata.vecinos(this.fila-1, this.columna+1)==3){
                                new Celula(automata, this.fila-1, this.columna-1);
                            }
                        case 4:
                            if (automata.vecinos(this.fila, this.columna-1)==3){
                                new Celula(automata, this.fila-1, this.columna-1);
                            }
                        case 5:
                            if (automata.vecinos(this.fila, this.columna+1)==3){
                                new Celula(automata, this.fila-1, this.columna-1);
                            }
                        case 6:
                            if (automata.vecinos(this.fila+1, this.columna-1)==3){
                                new Celula(automata, this.fila-1, this.columna-1);
                            }
                        case 7:
                            if (automata.vecinos(this.fila+1, this.columna)==3){
                                new Celula(automata, this.fila-1, this.columna-1);
                            }
                        case 8:
                            if (automata.vecinos(this.fila+1, this.columna+1)==3){
                                new Celula(automata, this.fila-1, this.columna-1);
                            }
                    }
            }
            cont++;
        }
    }

    
    /**Actualiza su estado actual considerando lo definido como siguiente estado
     */
    public final void cambie(){
        cumple();
        estado=estadoSiguiente;
    }
}
