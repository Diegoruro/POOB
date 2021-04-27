package dominio;

import javax.swing.*;
import java.util.ArrayList;

public class Gema {
    private int type;
    private String ruta;
    public static String[] rutas = {"./images/gema1.png", "./images/gema2.png", "./images/gema3.png",
            "./images/gema4.png", "./images/gema5.png"};

    /**
     * Constructor de una Gema
     * @param type Tipo de la gema
     */
    public Gema(int type){
        this.type = type;
        this.ruta = rutas[type];
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        setRuta(rutas[type]);
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
