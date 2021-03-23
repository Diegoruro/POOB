 

public class Maquina {
    private Ubicacion ubicacion;
    
    public Maquina(int longitud, int latitud){
        if (longitud < 0 || longitud >180 || latitud<-90 || latitud>90){
            System.out.println("Coordenadas invalidas");
        }else{
             this.ubicacion = new Ubicacion(longitud, latitud);
        }
    }
    
    public boolean isDebil(){
        return true;
    }
    
    public void alNorte(){
        ubicacion.alNorte();
    }
}
