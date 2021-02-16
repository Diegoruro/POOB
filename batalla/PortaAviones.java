import java.util.ArrayList;

public class PortaAviones {
    private int numero;
    private int capacidad;
    private Posicion ubicacion;
    private ArrayList<Marino> marinos;
    private ArrayList<Avion> aviones;
    
    PortaAviones(int numero,int capacidad)
    {
        this.numero=numero;
        this.capacidad=capacidad;
    }
    
     public int getNumero(){
        return this.numero;
    }
    
    public int getCapacidad(){
        return this.capacidad;
    }
}
