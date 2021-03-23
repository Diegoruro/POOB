import java.util.ArrayList;

public class Flota {
    private Tablero tablero;
    private String nombre;
    private ArrayList<Marino> marinos;
    private ArrayList<Maquina> maquinas;
    
    public void addBarco(int longitud, int latitud){
        maquinas.add(new Barco(longitud, latitud));        
    }
    
    public void addAvion(int longitud, int latitud){
        maquinas.add(new Avion(longitud, latitud));
    }
    
    public void addPortaAvion(int longitud, int latitud){
        maquinas.add(new PortaAviones(longitud, latitud));
    }
    
    public void alNorte(){
        for (Maquina maquina: maquinas){
            maquina.alNorte();
        }
    }
    
    public ArrayList<Maquina> maquinasDebiles(){
        ArrayList<Maquina> debiles = new ArrayList<Maquina>();
        for (Maquina maquina: maquinas){
            if (maquina.isDebil()){
                debiles.add(maquina);
            }
        }
        return debiles;
    }
}
