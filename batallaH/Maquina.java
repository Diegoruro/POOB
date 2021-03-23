 

public class Maquina {
    private Ubicacion ubicacion;
    public boolean isDestroyed;
    
    public Maquina(int longitud, int latitud){
        if (longitud < 0 || longitud >180 || latitud<-90 || latitud>90){
            System.out.println("Coordenadas invalidas");
        }else{
             this.ubicacion = new Ubicacion(longitud, latitud);
             this.isDestroyed = false;
        }
    }
    
    public boolean isDebil(){
        return true;
    }
    
    public void alNorte(){
        ubicacion.alNorte();
    }
    
    public Ubicacion getUbicacion(){
        return this.ubicacion;
    }
}
