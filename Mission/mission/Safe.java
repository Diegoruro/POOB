package mission;


/**
 * Esta caja no se puede robar
 */
public class Safe extends Caja
{

    /**
     * Constructor for objects of class Delicate
     * 
     * @param Bodega la bodega donde se guarda la caja
     *        i,j posicion a guardar la caja 
     */
    public Safe(Bodega bodega, int i, int j)
    {
        super(bodega, "safe", i,j);
        this.color = "pink";
    }

}
