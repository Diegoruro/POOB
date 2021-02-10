public class Posicion {
    private int longitud;
    private int latitud;
    
    
    public Posicion(int lat,int longi)
    {
        this.longitud=longi;
        this.latitud=lat;
    }
    
    
    public void movePosition(int setlatitud, int setlongitud)
    {
        this.longitud+=setlongitud;
        this.latitud+=setlatitud;
    }
}
