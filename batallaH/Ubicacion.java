 

public class Ubicacion {
    private int longitud;
    private int latitud;
    
    public Ubicacion(int longitud, int latitud){
        this.longitud = longitud;
        this.latitud = latitud;
    }
    
    public void alNorte(){
        if (latitud<89){
            latitud++;
        }
    }
    
    
}
