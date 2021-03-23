
/**
 * Write a description of class Capsula here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Capsula extends Maquina
{
    private Maquina nodriza;
    private Flota flota;
    
    /**
     * Constructor for objects of class Capsula
     */
    public Capsula(int longitud, int latitud,Flota flota)
    {
        super(longitud, latitud);
        this.flota = flota;
        
        for(Maquina maquina: flota.getMaquinas())
        {
            if(maquina.getUbicacion().getLatitud() == latitud && maquina.getUbicacion().getLongitud() == longitud && (maquina instanceof Barco || maquina instanceof Capsula))
            {
                this.nodriza = maquina;
            }
        }
    }
    
    
    public void selfDestroy(){
        if (this.nodriza.isDestroyed){
            System.out.println("Nodriza destruida");
            this.isDestroyed = true;
        }
    }
    
    @Override
    public boolean isDebil(){
        return false;
    }
}
