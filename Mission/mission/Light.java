package mission;


/**
 * Esta caja tiene la posibilidad de posicionarse encima de la caja delicada ya que es una caja ligera
 */
public class Light extends Caja
{
    

    /**
     * Constructor for objects of class Delicate
     * 
     * @param Bodega la bodega donde se guarda la caja
     *        i,j posicion a guardar la caja 
     */
    public Light(Bodega bodega, int i, int j)
    {
        super(bodega, "light", i,j);
        this.color = "lightGray";
    }
    
}