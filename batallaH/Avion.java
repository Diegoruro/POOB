 

public class Avion extends Maquina{
    private String placa;
    private boolean enAire;
    private Marino piloto = null;
    private Marino copiloto = null;
    
    public Avion(int longitud, int latitud){
        super(longitud, latitud);
    }
    
    public void addPiloto(String nombre, int rango){
        this.piloto = new Marino(nombre, rango);
    }
    
    public Marino getPiloto(){
        return this.piloto;
    }
    
    public Marino getCopiloto(){
        return this.copiloto;
    }
    
    @Override
    public boolean isDebil(){
        boolean cond = false;
        if (this.piloto == null){
            cond = true;
        }
        return cond;
    }
    
    /**
     * Autodestruye el avion
     *
     */
    public void selfDestroy(){
        this.isDestroyed = true;
        System.out.println("PortaAviones destruido");
    }
}
