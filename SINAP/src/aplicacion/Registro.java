package src.aplicacion;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

/**
 * Clase para registrar las excepciones en un archivo ".log"
 */
public class Registro{
    public static String nombre="SINAP";

    /**
     * Registra una excepcion en un archivo ".log" y termina el programa
     * @param e Excepcion a guardar
     */
    public static void registre(Exception e){
        try{
            Logger logger=Logger.getLogger(nombre);
            logger.setUseParentHandlers(false);
            FileHandler file=new FileHandler(nombre+".log",true);
            file.setFormatter(new SimpleFormatter());
            logger.addHandler(file);
            logger.log(Level.SEVERE,e.toString(),e);
            file.close();

            System.exit(0);
        }catch (Exception oe){
            oe.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Registra una excepcion en un archivo ".log"
     * @param e Excepcion a guardar
     */
    public static void registre2(Exception e){
        try{
            Logger logger=Logger.getLogger(nombre);
            logger.setUseParentHandlers(false);
            FileHandler file=new FileHandler(nombre+".log",true);
            file.setFormatter(new SimpleFormatter());
            logger.addHandler(file);
            logger.log(Level.SEVERE,e.toString(),e);
            file.close();
        }catch (Exception oe){
            oe.printStackTrace();
            System.exit(0);
        }
    }
}
    
