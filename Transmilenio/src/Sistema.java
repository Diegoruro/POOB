import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Clase principal del sistema de Transmilenio
 */
public class Sistema {
    private HashSet<Troncal> troncales;
    private ArrayList<Estacion> estaciones;
    private ArrayList<Ruta> rutas;

    /**
     * Constructor de la clase Sistema
     */
    public Sistema(){
        this.troncales = new HashSet<>();
        this.estaciones = new ArrayList<>();
    }

    /**
     * Método que calcula el número de paradas entre dos estaciones de una ruta
     * @param nombre Nombre de la ruta
     * @param estacion1 Nombre primera estación
     * @param estacion2 Nombre segunda estación
     * @return Cantidad de paradas
     */
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

    /**
     *  Metodo que calcula las mejores rutas entre dos estaciones
     * @param estacion1 Nombre primera estación
     * @param estacion2 Nombre segunda estación
     * @return Arreglo con los nombres de las mejores rutas
     */
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

    /**
     * Método que busca una estación
     * @param nombre Nombre de la estación
     * @return Estación
     */
    private Estacion findEstacion(String nombre){
        for (Estacion estacion: this.estaciones){
            if (estacion.getNombre().equals(nombre)){
                return estacion;
            }
        }
        return null;
    }

    /**
     * Método que busca una ruta
     * @param nombre Nombre de la ruta
     * @return Ruta
     */
    private Ruta findRuta(String nombre){
        for (Ruta ruta: this.rutas){
            if (ruta.getNombre().equals(nombre)){
                return ruta;
            }
        }
        return null;
    }

    /**
     * Método que calcula el tiempo recorrido de diferentes rutas
     * @param paradas Array de paradas
     * @return Tiempo que tarda
     */
    public int tiempoRecorrido(String[][] paradas){
        int time = 0;
        //Iteramos sobre cada tupla
        for (int i = 0; i < paradas.length; i++) {
            for (int j = 0; j < paradas[0].length; j++) {
                Estacion estacion = findEstacion(paradas[i][0]);
                Ruta ruta = findRuta(paradas[i][1]);
                assert ruta != null;
                boolean flag = false;
                if(ruta.getEstaciones().contains(estacion)){
                    for (Estacion aux: ruta.getEstaciones()){
                        //Como no tenemos el tiempo entre estaciones le sumamos 1 al tiempo por cada estación recorrida
                        if (aux == estacion){
                            flag = true;
                        }
                        if (flag){
                            time++;
                        }
                    }
                }
            }
        }
        return time;
    }

    /**
     * Método que guarda una troncal
     * @param troncal Troncal a guardar
     */
    public void guardarTroncal(Troncal troncal){
        JFileChooser chooser = new JFileChooser();
        int selected = chooser.showSaveDialog(null);


        if (selected == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();

            try {
                PrintWriter pw = new PrintWriter(new FileOutputStream(file.getAbsolutePath()));
                pw.println(troncal.getNombre());
                pw.println("Promedio Recorrido: " + troncal.getPromedioRecorrido());
                for (Estacion estacion: troncal.getEstaciones()){
                    pw.println("Estacion " + estacion.getNombre() + ", " + estacion.getNivelOcu() + ", " + estacion.gettEspera());
                }

                pw.close();

            } catch(FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    /**
     * Método que añade una troncal
     * @param troncal Troncal a añadir
     */
    public void addTroncal(Troncal troncal){
        this.troncales.add(troncal);
    }

    /**
     * Método que añade una estación
     * @param estacion Estación a añadir
     */
    public void addEstacion(Estacion estacion){
        this.estaciones.add(estacion);
    }

    public static void main(String[] args) {
        Sistema tm = new Sistema();
        tm.addTroncal(new Troncal("Dorado", 40));
        for (Troncal troncal: tm.troncales){
            if (troncal.getNombre().equals("Dorado")){
                troncal.addEstacion(new Estacion("Portal", 'a', 8));
                troncal.addEstacion(new Estacion("Modelia", 'b', 4));
                troncal.addEstacion(new Estacion("Normandía", 'b', 3));
                troncal.addEstacion(new Estacion("Av Rojas", 'm', 5));
                troncal.addEstacion(new Estacion("El tiempo", 'b', 4));
                tm.guardarTroncal(troncal);
            }
        }
    }
}
