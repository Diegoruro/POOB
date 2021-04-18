package src.aplicacion;
 
/**
 * Contiene los detalles de las áreas de reserva
 * @author POOB 01 
 * @version ECI 2016-1
 */

public class Area{
    private String nombre;
    private String name;
    private String ubicacion;
    private String area;    
    private String descripcion;

    /**
     * Crea un area con sus detalles
     * @param nombre nombre en español
     * @param name nombre en ingles
     * @param ubicacion departamento(s) donde su ubica
     * @param area area del lugar
     * @param descripcion descripcion breve del lugar
     */
    public Area(String nombre, String name,  String ubicacion, String area,String descripcion){
        this.nombre = nombre.trim();
        this.name = name.trim();
        this.ubicacion = ubicacion.trim();
        this.area = area.trim();
        this.descripcion = descripcion.trim();
    }
    
    /**
     * Getter del atributo nombre
     * @return Nombre
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Getter del atributo name
     * @return Nombre Internacional
     */
    public String getName(){
        return name;
    }

    /**
     * Getter del atributo ubicacion
     * @return Ubicacion
     */
    public String getUbicacion(){
        return ubicacion;
    }
    
    /**
     * Getter del atributo area
     * @return Area del lugar
     */
    public String getArea(){
        return area;
    }
    
    /**
     * Getter del atributo descripcion
     * @return Descripcion breve del lugar
     */
    public String getDescripcion(){
        return descripcion;
    }

    /**
     * Metodo para convertir los datos de un área a string
     * @return Una cadena con los nombres y la descripcion
     */
    public String toString(){
        return nombre + "\n" + name + "\n" + descripcion;
    }

}
