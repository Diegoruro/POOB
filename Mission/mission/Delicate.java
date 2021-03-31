package mission;


/**
 * Esta caja puede tener cajas debajo pero no encima porque es una caja fragil
 */
public class Delicate extends Caja
{
    /**
     * Constructor for objects of class Delicate
     * 
     * @param Bodega la bodega donde se guarda la caja
     *        i,j posicion a guardar la caja 
     */
    public Delicate(Bodega bodega, int i, int j)
    {
        super(bodega, "delicate", i,j);
        this.color = "gray";
    }
}
