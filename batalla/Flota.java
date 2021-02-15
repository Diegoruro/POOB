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
    public static ArrayList<Flota> flotas = new ArrayList<Flota>(2);

    public Flota(String nombre)
    {
        id = 123;
        minTrips = 500;
        score=new int[3];
        barcos=new ArrayList<Barco>();
        aviones = new ArrayList<Avion>();
        marinos = new ArrayList<Marino>();
        portaAviones = new ArrayList<PortaAviones>();
        this.flotas.add(this);
    }
    
    /**
     * 
     */
    public void createBarco(int num)
    {
        Barco barco = new Barco(num);
        barcos.add(barco);
        
    }
    
    
    /**
     * 
     */
    public void createAvion(String placa, boolean enAire)
    {
        Avion avion = new Avion(placa, enAire);
        aviones.add(avion);
        
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

    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public  ArrayList<String> enAire()
    {
         Flota enemigo;
         ArrayList<String> ans = new  ArrayList<String>();
         if (this.nombre == this.flotas.get(0).nombre){
             enemigo = this.flotas.get(1);
         }else{
             enemigo = this.flotas.get(0);
         }
         System.out.println(enemigo);
         for (int i = 0; i < enemigo.aviones.size();i++){
             System.out.println(enemigo.aviones.get(i).getEnAire());
             if (enemigo.aviones.get(i).getEnAire()){
                 ans.add(enemigo.aviones.get(i).getPlaca());
             }
         }
         return ans;
    }

}
