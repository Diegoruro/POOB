package aplicacion;

public class SINAPExcepcion extends Exception{
    public static final String SIN_NOMBRE_INT = "Debe ingresar un nombre internacional";

    public SINAPExcepcion(String message) {
        super(message);
    }
}
