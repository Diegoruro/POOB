package domain;

public class AutomataExcepcion extends Exception{
    public static final String OPCION_EN_CONSTRUCCION = "Opcion en costruccion";
    public static final String ERROR_GUARDAR = "Ocurrió un error al guardar el archivo";
    public static final String ERROR_ABRIR = "Ocurrió un error al abrir el archivo";
    public static final String ERROR_EXPORTAR = "Ocurrió un error al exportar el archivo";
    public static final String ERROR_TIPO_ABRIR = "Error Abrir: El tipo de archivo es incorrecto (Deben ser archivos '.dat')";
    public static final String ERROR_TIPO_GUARDAR = "Error Guardar: El tipo de archivo es incorrecto (Deben ser archivos '.dat')";
    public static final String ERROR_TIPO_IMPORTAR = "Error Importar: El tipo de archivo es incorrecto (Deben ser archivos '.txt')";
    public static final String ERROR_TIPO_EXPORTAR = "Error Exportar: El tipo de archivo es incorrecto (Deben ser archivos '.txt')";
    public static final String ERROR_ELEMENTO_INVALIDO = "Error Importar: Ingreso un tipo de célula o elemento inválido";
    public static final String ERROR_NUMERO = "Error Importar: Debe ingresar las coordenadas de la célula de forma numérica";
    public static final String ERROR_INFORMACIÓN = "Error Importar: Debe ingresar 4 datos separados por espacios";
    public static final String ERROR_CREAR = "Error Importar: No se pudo crear la célula";

    public AutomataExcepcion(String message) {
        super(message);
    }
}
