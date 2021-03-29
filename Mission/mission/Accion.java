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

    /**
     * Constructor for objects of class Accion
     */
    public Accion(String action, Integer[] coordenadas)
    {
        this.coordenadas = coordenadas;
        this.action = action;
    }

}
