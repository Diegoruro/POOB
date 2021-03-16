package domain;
import java.awt.Color;

/**
 * Write a description of class CelulaEspecial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CelulaEspecial extends Celula 
{
    public boolean estaSola=true;
    public boolean nulls=false;
    /**
     * Constructor for objects of class CelulaEspecial
     */
    public CelulaEspecial(AutomataCelular ac, int fila, int columna)
    {
        super(ac,fila,columna);
        color=Color.green;
    }

    
    public void decida(Elemento [] vecinos){
        this.nulls=false;
        this.estaSola=true;
        for (int i=0;i<8;i++){
            if (vecinos[i] != null && vecinos[i].isVivo()){
                this.estaSola = false;
            }
            if(vecinos[i] == null){
                this.nulls = true;
            }
        }
        
        if (this.estaSola && !this.nulls){
            estadoSiguiente = 'm';
        }
        else if(!this.estaSola){
            estadoSiguiente = 'v';
        }
        
        if (this.nulls && this.estaSola){
            automata.newCelula(this.fila,this.columna);
        }
    }
    
    
}
