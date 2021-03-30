package mission;


/**
 * Write a description of class Rebel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rebel extends Caja
{
    
    /**
     * Constructor for objects of class Rebel
     */
    public Rebel(Bodega bodega, int i, int j)
    {
        super(bodega, "rebel", i,j);
        this.color = "orange";
    }

    @Override
    public void store(){
        if (this.bodega.valores[j-1][i-1]>=1){
            if (this.bodega.top[j][i].getTipo() == "delicate"){
                //exepcion
            }
            this.bodega.store(this.j, this.i);
        }
        this.bodega.store(this.j,this.i);
    }
}
