import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Sistema {
    private HashSet<Troncal> troncales;
    private ArrayList<Estacion> estaciones;
    private ArrayList<Ruta> rutas;

    public Sistema(){
        this.troncales = new HashSet<>();
        this.estaciones = new ArrayList<>();
    }

    public int nParadas(String nombre, String estacion1, String estacion2){
        int cont=-1;
        for (Ruta ruta: rutas) {
            if (ruta.getNombre().equals(nombre)){
                for (Estacion estacion: ruta.getEstaciones()){
                    if (estacion.getNombre().equals(estacion1)){
                        cont = 0;
                    }else if (estacion.getNombre().equals(estacion2)){
                        return cont;
                    }else{
                        cont++;
                    }

                }
            }
        }
        return cont;
    }

    public ArrayList<String> mejoresRutas(String estacion1, String estacion2){
        int cantidad;
        ArrayList<String> bestRoutes = new ArrayList<>();
        HashMap<String, Integer> ar = new HashMap<>();

        Estacion e1 = findEstacion(estacion1);
        Estacion e2 = findEstacion(estacion2);

        for (Ruta ruta: rutas){
            cantidad = 0;
            if (ruta.getEstaciones().contains(e1) && ruta.getEstaciones().contains(e2)){
                cantidad = nParadas(ruta.getNombre(), estacion1, estacion2);
                ar.put(ruta.getNombre(), cantidad);
            }
        }

        for(String nombre: ar.keySet()){
             bestRoutes.add(nombre+": " + ar.get(nombre));
        }

        bestRoutes.sort(String::compareToIgnoreCase);
        return bestRoutes;
    }

    private Estacion findEstacion(String nombre){
        for (Estacion estacion: this.estaciones){
            if (estacion.getNombre().equals(nombre)){
                return estacion;
            }
        }
        return null;
    }

    private Ruta findRuta(String nombre){
        for (Ruta ruta: this.rutas){
            if (ruta.getNombre().equals(nombre)){
                return ruta;
            }
        }
        return null;
    }

    public int tiempoRecorrido(String[][] paradas){
        int time = 0;
        for (int i = 0; i < paradas.length; i++) {
            for (int j = 0; j < paradas[0].length; j++) {
                Estacion estacion = findEstacion(paradas[i][0]);
                Ruta ruta = findRuta(paradas[i][1]);
                assert ruta != null;
                if(ruta.getEstaciones().contains(estacion)){
                    for (Estacion aux: ruta.getEstaciones()){

                    }
                }
            }
        }
        return time;
    }
}
