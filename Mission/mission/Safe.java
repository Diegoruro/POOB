package mission;


/**
 * Write a description of class Safe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Safe extends Caja
{

    /**
     * Constructor for objects of class Safe
     */
    public Safe(Bodega bodega, int i, int j)
    {
        super(bodega, "safe", i,j);
        this.color = "pink";
    }

}
