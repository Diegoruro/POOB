package mission;


/**
 * Write a description of class MissionException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MissionException extends Exception
{
    public static final String INVALIDSTORE = "No se puede guardar";
    public static final String INVALIDSTEAL = "No se puede robar";
    public static final String INVALIDARRANGE = "No se puede mover";

    /**
     * Constructor for objects of class MissionException
     */
    public MissionException(String message)
    {
        super(message);
    }

}
