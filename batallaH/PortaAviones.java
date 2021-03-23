import java.util.ArrayList;

public class PortaAviones extends Barco{
    private int capacidad;
    private ArrayList<Avion> aviones;
    
    public PortaAviones(int longitud, int latitud){
        super(longitud, latitud);
    }
    
    public void addAvion(int longitud, int latitud){
        this.aviones.add(new Avion(longitud, latitud));
    }
    
    @Override
    public boolean isDebil(){
        int marinos = 0;
        for (Avion avion: aviones){
            if (avion.getPiloto() != null){
                marinos += 1;
            }
            if (avion.getCopiloto() != null){
                marinos += 1;
            }
            if (avion.isDebil()){
                return true;
            }
        }
        if (marinos < 5){
            return true;
        }
        return false;
    }
}
