import java.util.ArrayList;

public class Flota {
    public final int id;
    public final int minTrips;
    public int [] score;
    public String nombre;
    private int avionesCargados=0;
    private int capacidadPorta=0;
    private ArrayList<Barco> barcos;
    private ArrayList<Avion> aviones;
    private ArrayList<PortaAviones> portaAviones;
    private ArrayList<Marino> marinos;
    public static ArrayList<Flota> flotas = new ArrayList<Flota>(2);

    
    /**
     * Flota Constructor
     *
     * @param nombre: Nombre propio de la flota
     */
    public Flota(String nombre)
    {
        id = 123;
        minTrips = 500;
        this.nombre=nombre;
        score=new int[3];
        barcos=new ArrayList<Barco>();
        aviones = new ArrayList<Avion>();
        marinos = new ArrayList<Marino>();
        portaAviones = new ArrayList<PortaAviones>();
        this.flotas.add(this);
    }
    
    
    /**
     * Método createBarco: Crea un barco con su respectivo numero identificador
     *
     * @param num: Numero identificador del barco
     */
    public void createBarco(int num)
    {
        Barco barco = new Barco(num);
        barcos.add(barco);
    }
    
    
    /**
     * Método createAvion: Crea un avion con su respectiva placa y se indica si este esta volando o esta cargado en un portaavion.
     *
     * @param placa: Placa del avion
     * @param enAire: Boolean que indica si el avion esta en el aire o en un portaavion 
     */
    public void createAvion(String placa, boolean enAire)
    {
        Avion avion = new Avion(placa, enAire);
        if (enAire==false&&this.portaAviones.size()>0)
        {
            avionesCargados++;
        }
        else if (enAire==false&&this.portaAviones.size()==0)
        {
            System.out.println("no tienes porta-aviones donde dejar este avion");
        }
        aviones.add(avion);
        
    }
    
    
    
    /**
     * Método createPortaAviones: Crea un portaavion con su respectivo numero y la capacidad que tiens¿e
     *
     * @param numero: Numero identificador del portaavion
     * @param capacidad: La capacidad del portaavion
     */
    public void createPortaAviones(int numero,int capacidad)
    {
        PortaAviones portaAvion= new PortaAviones(numero, capacidad);
        this.capacidadPorta=this.capacidadPorta+capacidad;
        portaAviones.add(portaAvion);
    }


    /**
     * Método muevase: Mueve todos los barcos en una misma direccion
     *
     * @param deltaLongitud: Longitud del movimiento
     * @param deltaLatitud: Longitud del movimiento
     */
    public void muevase(int deltaLongitud,int deltaLatitud)
    {
        for(Barco b:barcos)
        {
            b.movePosition(deltaLongitud,deltaLatitud);
        }
    }

    
    
    /**
     * Método enAire: Halla todas las placas de los aviones enemigos que estan volando.
     *
     * @return Una ArrayList con las placas de los aviones enemigos que estan volando.
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
         
         for (int i = 0; i < enemigo.aviones.size();i++){
             if (enemigo.aviones.get(i).getEnAire()){
                 ans.add(enemigo.aviones.get(i).getPlaca());
             }
         }
         return ans;
    }

    
    
    /**
     * Método disponibilidadEnPortaAviones: Calcula la capacidad total para cargar aviones en los portaaviones
     *
     * @return la capacidad total de los portaaviones
     */
    public int disponibilidadEnPortaAviones()
    {
       return this.capacidadPorta-this.avionesCargados;
    }

    
    
    /**
     * Método problemaEnAire: Compara y verifica si es posible confundir la placa de un avion aliado con la placa de un avion enemigo.
     *
     * @return si es posible confundirse.
     */
    public boolean problemaEnAire()
    {
        boolean ans=false;
        Flota enemigo;
         if (this.nombre == this.flotas.get(0).nombre){
             enemigo = this.flotas.get(1);
         }else{
             enemigo = this.flotas.get(0);
         }
         
        for (int i=0;i<this.aviones.size();i++)
        {
            for(int j=0;j<enemigo.aviones.size();j++)
            {
                if (this.aviones.get(i).getPlaca()==enemigo.aviones.get(j).getPlaca())
                {
                    ans=true;
                }
            }
        }
        return ans;
    }

}
