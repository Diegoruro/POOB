package src.aplicacion;

public class SINAPExcepcion extends Exception{
    public static final String SIN_NOMBRE_INT = "Debe ingresar un nombre internacional";
    public static final String NOMBRE_REPETIDO = "Ya existe un área con este nombre";
    public static final String DESCRIPCION_MUY_LARGA = "La descripción no debe sobrepasar los 1000 caracteres";
    public static final String SIN_UBICACION = "Debe ingresar una ubicación";

    public SINAPExcepcion(String message) {
        super(message);
    }
}
