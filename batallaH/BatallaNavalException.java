public class BatallaNavalException extends Exception
{
    public static final String POTENCIAFLOTA = "Hay más maquinas que marinos en la flota";
    public static final String POTENCIATABLERO = "Más de la mitad de las flotas tienen problemas de potencia";
    
    /**
     * Constructor for objects of class BatallaNavalException
     * @param Exception message
     */
    public BatallaNavalException(String message)
    {
        super(message);
    }
}
