package domain;
import java.awt.Color;

/**
 * comportamientos de una célula asustada.
 * 
 * @author (Juan Aguas - Diego Ruiz) 
 * @version (1)
 */
public class CelulaAsustada extends Celula 
{
    public boolean estaSola=true;
    public boolean nulls=false;
    /**
     */
    public CelulaAsustada(AutomataCelular ac, int fila, int columna)
    {
        super(ac,fila,columna);
        color=Color.magenta;
    }

    /**
    @see Elemento.decida()
     */
    public void decida(Elemento [] vecinos){
        int muertos = getMuertos(vecinos);
        if (muertos==8){
            this.estadoSiguiente='m';
            color=Color.red;
        }
        else if(muertos>=5){
            this.estadoSiguiente='v';
            color=Color.orange;
        }
        else if(muertos>=3){
            this.estadoSiguiente='v';
            color=Color.pink;
        }
        else{
            this.estadoSiguiente='v';
        }
    }
    
    /**Calcula los vecinos muertos al rededor de una celula
    @param Elemento[] vecinos lista de vecinos alrededor una celula
    @return la cantidad de vecinos muertos
     */
    private int getMuertos(Elemento [] vecinos){
        int muertos = 0;
        for (int i = 0;i<vecinos.length;i++){
            if (vecinos[i]!=null && !vecinos[i].isVivo()){
                muertos++;
            }
        }
        return muertos;
    }
}
