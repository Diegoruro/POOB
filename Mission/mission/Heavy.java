package mission;


/**
 *Esta caja solo puede tener cajas encima y no debajo porque es una caja pesada
 */
public class Heavy extends Caja
{
    /**
     * Constructor for objects of class Delicate
     * 
     * @param Bodega la bodega donde se guarda la caja
     *        i,j posicion a guardar la caja 
     */
    public Heavy(Bodega bodega, int i, int j)
    {
        super(bodega, "heavy", i,j);
        this.color = "black";
    }
}