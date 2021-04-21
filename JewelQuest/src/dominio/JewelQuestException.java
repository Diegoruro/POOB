package dominio;

public class JewelQuestException extends Exception{
    public static final String MOVIMIENTO_INVALIDO = "No se puede realizar este movimiento";

    public JewelQuestException(String message){
        super(message);
    }
}
