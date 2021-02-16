public class Posicion {
    private int longitud;
    private int latitud;
    
    
    public Posicion(int lat,int longi)
    {
        this.longitud=longi;
        this.latitud=lat;
    }
    
    
    /**
     * Retorna la latitud
     */
    public int getLatitud(){
        return this.latitud;
    }
    
    
    /**
     * Retorna la longitud
     */
    public int getLongitud(){
        return this.longitud;
    }
    
    
    public void movePosition(int setlatitud, int setlongitud)
    {
        this.longitud+=setlongitud;
        this.latitud+=setlatitud;
    }
}
