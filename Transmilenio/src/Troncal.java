import java.util.LinkedList;

public class Troncal {
    private String nombre;
    private float promedioRecorrido;
    private LinkedList<Integer> tramos;
    private LinkedList<Estacion> estaciones;

    public Troncal(String nombre, float promedioRecorrido){
        this.nombre = nombre;
        this.promedioRecorrido = promedioRecorrido;
        this.tramos = new LinkedList<>();
        this.estaciones = new LinkedList<>();
    }

}
