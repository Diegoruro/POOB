package aplicacion; 
 
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
     * @param area
     * @param descripcion 
     */
    public Area(String nombre, String name,  String ubicacion, String area,String descripcion){
        this.nombre = nombre.trim();
        this.name = name.trim();
        this.ubicacion = ubicacion.trim();
        this.area = area.trim();
        this.descripcion = descripcion.trim();
    }
    
    /**
     * @return 
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * @return 
     */
    public String getName(){
        return name;
    }

    /**
     * @return 
     */
    public String getUbicacion(){
        return ubicacion;
    }
    
    /**
     * @return 
     */
    public String getArea(){
        return area;
    }
    
    /**
     * @return 
     */
    public String getDescripcion(){
        return descripcion;
    }

    /**
     * @return Una cadena con los nombres y la descripcion
     */
    public String toString(){
        return nombre + "\n" + name + "\n" + descripcion;
    }

}
