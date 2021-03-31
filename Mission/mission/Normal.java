package mission;


/**
 * Esta es una caja comun y corriente
 */
public class Normal extends Caja
{
    

    /**
     * Constructor for objects of class Delicate
     * 
     * @param Bodega la bodega donde se guarda la caja
     *        i,j posicion a guardar la caja 
     */
    public Normal(Bodega bodega, int i, int j)
    {
        super(bodega, "normal", i,j);
        this.color = "blue";
    }
    
}
