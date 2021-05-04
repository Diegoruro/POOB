import java.util.LinkedList;

public class Ruta {
    private String nombre;
    private LinkedList<Estacion> estaciones;

    public Ruta(String nombre){
        this.nombre = nombre;
        this.estaciones = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public LinkedList<Estacion> getEstaciones() {
        return estaciones;
    }
}
