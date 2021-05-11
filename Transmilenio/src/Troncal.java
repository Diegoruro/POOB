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

    public String getNombre() {
        return nombre;
    }

    public float getPromedioRecorrido() {
        return promedioRecorrido;
    }

    public LinkedList<Integer> getTramos() {
        return tramos;
    }

    public LinkedList<Estacion> getEstaciones() {
        return estaciones;
    }

    public void addEstacion(Estacion estacion){
        this.estaciones.add(estacion);
    }
}
