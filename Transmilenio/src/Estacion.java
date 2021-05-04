public class Estacion {
    private String nombre;
    private char nivelOcu;
    private int tEspera;

    public Estacion(String nombre, char nivelOcu, int tEspera){
        this.nombre = nombre;
        this.nivelOcu = nivelOcu;
        this.tEspera = tEspera;
    }

    public String getNombre() {
        return nombre;
    }
}
