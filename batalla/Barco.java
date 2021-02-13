import java.util.Collection;

public class Barco {

    private int numero;

    private Posicion ubicacion;

    private Collection<Marino> marinos;
    
    
    public Barco(int num)
    {
        this.ubicacion=new Posicion(0,0);
        this.numero=num;
    }
    
    
    public void movePosition(int deltaLongitud,int deltaLatitud)
    {   //cambiar posicion de los barcos
        this.ubicacion.movePosition(deltaLongitud,deltaLatitud);
    }
}
