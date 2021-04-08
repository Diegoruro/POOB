 

public class Ubicacion {
    private int longitud;
    private int latitud;
    
    public Ubicacion(int longitud, int latitud){
        this.longitud = longitud;
        this.latitud = latitud;
    }
    
    /**
     * Aumenta la ubicación al norte
     *
     */
    public void alNorte(){
        if (latitud<89){
            latitud++;
        }
        //[-90,90]
    }
    
    public int getLatitud(){
        return this.latitud;
    }
    
    public int getLongitud(){
        return this.longitud;
    }
}
