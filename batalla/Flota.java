import java.util.ArrayList;

public class Flota {
    public final int id;
    public final int minTrips;
    public int [] score;
    public String nombre;
    private ArrayList<Barco> barcos;
    private ArrayList<Avion> aviones;
    private ArrayList<PortaAviones> portaAviones;
    private ArrayList<Marino> marinos;
    public static ArrayList<Flota> flotas=new ArrayList();

    public Flota(String nombre)
    {
        id = 123;
        minTrips = 500;
        score=new int[3];
        
    }
    
    public void createBarco(int num)
    {
        barcos=new ArrayList<Barco>();
        Barco barco=new Barco(num);
        barcos.add(barco);
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void muevase(int deltaLongitud,int deltaLatitud)
    {
        for(Barco b:barcos)
        {
            b.movePosition(deltaLongitud,deltaLatitud);
        }
    }

}
