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
     * Constructor for objects of class Accion
     */
    public Accion(String action,String tipo, Integer[] coordenadas)
    {
        this.coordenadas = coordenadas;
        this.action = action;
        this.tipo = tipo;
    }

}
