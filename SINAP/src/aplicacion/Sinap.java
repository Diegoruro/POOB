package src.aplicacion;

import javax.swing.*;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Sinap contiene la informacion del Sistema Nacional de Areas Protegidas
 * @author POOB 01 - Juan Aguas - Diego Ruiz
 * @version ECI 
 */

public class Sinap{
    private LinkedList <Area> areas;
    private static final int maxChars = 1000;


    /**
     * Inicia un kiosko vacio
     */
    public Sinap(){
        areas = new LinkedList<Area>();
    }
    
    /**
     * Adiciona cinco areas
     */
    public void adicioneCinco(){
        	Area ejemplos[] = {
	        new Area("Ciénaga Grande de Santa Marta","Large Marsh of Saint Martha", "Magdalena","4280" ,
	        "Santuario de flora y fauna."+
            " Propender por la recuperación del modelo hidráulico del complejo lagunar CGSM en el área del Santuario,"+
            " con el fin de garantizar el estado de conservación de la biodiversidad de flora y fauna y la productividad pesquera,"+
            " para mantener los flujos de nutrientes y cumplir con los protocolos internacionales de Reserva de Biosfera y "+
            " humedal Ramsar."),
            new Area( "Nevado del Huila","Nevado del Huila", " Huila, Tolima, Cauca","1.580 km²",
            "Parque natural"+
            " Es considerada estratégica ya que abastece las dos cuencas mas importantes del país "+
            "(Cuenca alta del Río Magdalena y Cuenca alta del Río Cauca) catalogándola como una estrella hídrica "+
            "del macizo colombiano, que aporta bienes y servicios ambientales representados en ecosistemas de Páramo,"+
            "subparamo, bosque Andino y altoandino, favoreciendo asi la viabilidad de especies de flora y fauna."),
            new Area("Laguna de Sonso", "Laguna de Sonso",  "Valle del Cauca", " 14,1 km²",
            "Reserva natural,"+
            "Aves migratorias que durante el invierno viajan desde Norteamérica en busca de buen clima "+
            "y turistas de todo el mundo encuentran en la Reserva Natural Laguna de Sonso, 10 km al suroccidente de Buga, "+
            "un sitio apacible moldeado por el río Cauca que cuenta con una torre de observación de 10 m de altura."),
            new Area("Morichales de Paz de Ariporo","Morichales de Paz de Ariporo", "Casanare", "",
            "Propuesta de parque natural"+
            " El ecosistema lo integra un humedal de 25 mil hectáreas donde impera la variedad de palma de moriche "+
            "que sirve de refugio a buena parte de la fauna llanera y donde igualmente residen unas 200 familias que por décadas "+
            "han habitado este paradisíaco lugar."),
            new Area("Parque Isla de Salamanca","Salamanca Island Road Park","Magdalena", " 	562 km²",
            "Vía parque'."+
            "Posee 98 especies de invertebrados, 9 especies de anfibios, 35 especies de reptiles, más de 140 especies de peces, "+
            "199 de aves, muchas de ellas migratorias, endémicas y residentes. La existencia de 33 especies de mamíferos indica "+
            "que la diversidad que sostiene el área es elevada, a pesar de la problemática ambiental que afectó el ecosistema. "+
            "El Vía Parque Isla de Salamanca cuenta con hábitats o refugios en donde se pueden observar especies declaradas en "+
            "peligro de extinción por presión del hombre, destrucción y fragmentación de hábitats y disminución de la oferta "+
            "alimentaria, entre otros factores.")
        };
        for(Area detalles : ejemplos) {
            try {
                adicioneDetalles(detalles);
            }catch (SINAPExcepcion e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }

    /**
     * Devuelve la lista de areas guardadas
     * @return Lista de areas
     */
    public LinkedList<Area> getAreas(){
        return this.areas;
    }
    
    /**
     * Consulta los detalles de una area
     * @param nombre El nombre de la area a buscar
     * @param name El nombre internacional de la area a buscar
     * @return Los detalles correspondientes a ese nombre
     */
    public Area getDetalles(String nombre,String name){
	Area c=null;
	for(int i=0;i<areas.size() && c == null;i++){
	    if (areas.get(i).getNombre().compareToIgnoreCase(nombre)==0 &&
               (areas.get(i).getName().compareToIgnoreCase(name)==0)){
               c=areas.get(i);
            }
	}
	return c;
    }


    /**
     * Adiciona una nueva Area a la Kiosco
     * @param nombre Nombre del area
     * @param name Nombre del area en ingles
     * @param area area del lugar
     * @param ubicacion Ubicación del area
     * @param descripcion Breve descripción del lugar
     */
    public void adicione(String nombre, String name,String ubicacion, String area, String descripcion) throws SINAPExcepcion {

        adicioneDetalles(new Area(nombre,name, ubicacion, area, descripcion));

    }

    /**
     * Adiciona una nueva area al SINAP
     * @param detalles El detalle asociado con la area
     */
    public void adicioneDetalles(Area detalles) throws SINAPExcepcion {
        if(detalles.getName().equals("") || detalles.getName() == null) {
            throw new SINAPExcepcion(SINAPExcepcion.SIN_NOMBRE_INT);
        }else if (detalles.getUbicacion().equals("")){
            throw new SINAPExcepcion(SINAPExcepcion.SIN_UBICACION);
        }
        int i = 0;
        while ((i < areas.size()) && (areas.get(i).getNombre().compareToIgnoreCase(detalles.getNombre()) < 0)) {
            i++;
        }
        if (detalles.getDescripcion().length()>maxChars){
            throw new SINAPExcepcion(SINAPExcepcion.DESCRIPCION_MUY_LARGA);
        }
        inAreas(detalles.getNombre());
        areas.add(i, detalles);

    }


    /**
     * Verifica si un área ya está guardada
     * @param nombre El nombre a consultar
     * @throws SINAP Excepcion si el nombre esta repetido
     */
    private void inAreas(String nombre) throws SINAPExcepcion {
        for (Area area: this.areas){
            if (area.getNombre().equals(nombre)){
                throw new SINAPExcepcion(SINAPExcepcion.NOMBRE_REPETIDO);
            }
        }
    }
    

    
    /**
     * Consulta las areas que inican con un prefijo
     * @param prefijo El prefijo a buscar
     * @return Los detalles encontrados
     */
    public ArrayList<Area> busque(String prefijo){
    ArrayList<Area> resultados= new ArrayList<Area>();
	prefijo=prefijo.toUpperCase();
	for(int i=0;i<areas.size();i++){
	    if(areas.get(i).getNombre().toUpperCase().startsWith(prefijo)){
	       resultados.add(areas.get(i));
	    }	
	}
        return resultados;
    }

    /**
     * Consulta el numero de areas
     * @return Numero de areas
     */
    public int numeroAreas(){
        return areas.size();
    }


    /**
     * Consulta todas las areas
     *  @return Todas las areas 
     */
    public String toString(){
	StringBuffer allEntries=new StringBuffer();
        for(Area detalles : areas) {
            allEntries.append(detalles.toString().length()<=150? detalles:detalles.toString().substring(0,149)+"...");
            allEntries.append('\n');
            allEntries.append('\n');
        }
        return allEntries.toString();
    }
}
