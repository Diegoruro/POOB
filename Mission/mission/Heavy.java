package mission;


/**
 * Write a description of class Heavy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Heavy extends Caja
{
    /**
     * Constructor for objects of class Heavy
     */
    public Heavy(Bodega bodega, int i, int j)
    {
        super(bodega, "heavy", i,j);
        this.color = "black";
    }

    @Override
    public void store(){
        if (this.bodega.valores[i][j]==0){
            this.bodega.store(this.i, this.j);
        }else{
            //Exepcion
        }        
    }
}
