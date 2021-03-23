 

import java.util.ArrayList;

public class Barco extends Maquina{
    private int numero;
    private ArrayList<Marino> marinos;
    
    public Barco(int longitud, int latitud){
        super(longitud, latitud);
        this.numero = 0;
    }
    
    public void addMarino(String nombre, int rango){
        this.marinos.add(new Marino(nombre, rango));
    }
    
    @Override
    public boolean isDebil(){
        boolean cond = false;
        if (marinos.size() < 5){
            cond = true;
        }
        return cond;
    }
}
