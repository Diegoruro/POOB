package mission;

import shapes.*;

/**
 * Write a description of class Caja here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Caja extends Rectangle
{
    protected Bodega bodega;
    private String tipo;
    public int i, j;
    public Caja caja;
    

    /**
     * Constructor for objects of class Caja
     */
    public Caja(Bodega bodega,String tipo, int i, int j)
    {
        this.bodega = bodega;
        this.tipo = tipo;
        this.i = i;
        this.j = j;
    }
    
    /**
     * Constructor for objects of class Caja
     */
    public Caja(Bodega bodega, int i, int j)
    {
        this.bodega = bodega;
        this.i = i;
        this.j = j;
        this.color = "green";
    }
    
    public void crear(){
        switch(this.tipo){
            case "normal":
                caja = new Normal(this.bodega, this.i, this.j);
                //cajas[i][j] = caja;
                caja.store();
            case "delicate":
                caja = new Delicate(this.bodega, this.i, this.j);
                caja.store();
            case "rebel":
                caja = new Rebel(this.bodega, this.i, this.j);
                caja.store();
            case "frost":
                caja = new Frost(this.bodega, this.i, this.j);
                caja.store();
            case "safe":
                caja = new Safe(this.bodega, this.i, this.j);
                caja.store();
            case "heavy":
                caja = new Heavy(this.bodega, this.i, this.j);
                caja.store();
                
        }
    }
    
    public void store(){
        if (this.bodega.valores[i-1][j-1]>=1){
            if (this.bodega.top[i][j].getTipo() == "delicate"){
                //exepcion
            }
            this.bodega.store(this.i, this.j);
        }
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
        this.crear();
    }
}
