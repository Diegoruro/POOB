package mission;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Write a description of class Accion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Accion
{
    public Integer[] coordenadas;
    public String action;
    public String tipo;

    /**
     * Guarda las acciones que se han realizado en la bodega/plan
     *
     * @param int[] coordenadas Posición de la caja que se manipulo
     *        String action La accion realizada
     *        String tipo El tipo de caja que se manipulo
     */
    public Accion(String action,String tipo, Integer[] coordenadas)
    {
        this.coordenadas = coordenadas;
        this.action = action;
        this.tipo = tipo;
    }

}
