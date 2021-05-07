package domain;
import java.awt.Color;

/**
 * Comportamientos de una celula del Juego De La Vida.
 * 
 * @author (Juan Aguas - Diego Ruiz) 
 * @version (1)
 */
public class CelulaConway extends Celula
{

    /**
     * Constructor for objects of class JuegoDeLaVida
     */
    public CelulaConway(AutomataCelular ac,int fila, int columna)
    {
        super(ac,fila,columna);
        color=Color.blue;
        estado = VIVO;
    }

    /**Decide cual va a ser su  siguiente estado 
     */
    public void decida(Elemento [] vecinos){
        int vivos=automata.vecinosVivos(vecinos);
        if(this.isVivo()&&(vivos==2||vivos==3)){
            this.estadoSiguiente='v';
        }
        if(!this.isVivo()&&vivos==3){
            this.estadoSiguiente='v';
        }
        if(this.isVivo()&&(vivos<2||vivos>3)){
            this.estadoSiguiente='m';
        }
    }
    
    
}
