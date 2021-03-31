package mission;


/**
 * Esta caja no la detecta la camara
 */
public class Frost extends Caja
{

    /**
     * Constructor for objects of class Delicate
     * 
     * @param Bodega la bodega donde se guarda la caja
     *        i,j posicion a guardar la caja 
     */
    public Frost(Bodega bodega, int i, int j)
    {
        super(bodega, "frost", i,j);
        this.color = "cyan";
    }

}
