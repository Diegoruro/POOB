package mission;


/**
 * Esta caja se posiciona en las ubicacion pero al reves
 */
public class Rebel extends Caja
{
    
    /**
     * Constructor for objects of class Delicate
     * 
     * @param Bodega la bodega donde se guarda la caja
     *        i,j posicion a guardar la caja 
     */
    public Rebel(Bodega bodega, int i, int j)
    {
        super(bodega, "rebel", i,j);
        this.color = "orange";
    }

}
