import java.util.ArrayList;

public class Flota {
    public final int id;
    public final int minTrips;
    public final String marino;
    public int [] score;
    private ArrayList<Barco> barcos;
    private ArrayList<Avion> aviones;
    private ArrayList<PortaAviones> portaAviones;
    private ArrayList<Marino> marinos;

    public Flota(){
        id = 123;
        minTrips = 500;
        marino="nombre generico";
        score=new int[3];
         
    }

}
