 

public class Marino {
    private String nombre;
    private int rango;
    
    public Marino(String nombre, int rango){
        this.nombre = nombre;
        this.rango = rango;
    }
    
    public void selfDestroy(){
        this.nombre = null;
    }
}
