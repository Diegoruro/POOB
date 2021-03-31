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
    

    /**
     * Constructor for objects of class Delicate
     * 
     * @param Bodega bodega La bodega donde se guarda la caja
     *        String tipo El tipo de caja que va a guardar
     *        int i,j posición a guardar la caja 
     */
    public Caja(Bodega bodega,String tipo, int i, int j)
    {
        this.bodega = bodega;
        this.tipo = tipo;
        this.i = i-1;
        this.j = j-1;
        this.color = "green";
    }    
    
    public String getTipo(){
        return this.tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
}
