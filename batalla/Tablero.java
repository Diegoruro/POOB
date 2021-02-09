import java.util.ArrayList;

public class Tablero {
    public final int[] latitud;
    public final int[] longitud;
    
    public Tablero()
    {
       latitud=new int[2];
       longitud=new int[2];
       latitud[0]=-100;
       latitud[1]=100;
       longitud[0]=-100;
       longitud[1]=100;
    }
}
