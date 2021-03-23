package domain;
import java.awt.Color;

/**
* Funcionalidades de cualquier elemento
*/
public interface Elemento{
  int REDONDA = 1;
  int CUADRADA = 2;


  /**Decide cual va a ser su  siguiente estado 
    @param Elemento [] vecinos lista de los vecinos que rodean al elemento
     */
  default void decida(Elemento[] vecinos){
  };
   
  /**Actualiza su estado actual considerando lo definido como siguiente estado
     */
  default void cambie(){
  };
  
  /**Retorna la forma del elemento
    @return 1 si es redondo, 2 si es cuadrado
     */
  default int forma(){
      return REDONDA;
  }
  
  /**Retorna el color de  la célula
     */
  default Color getColor(){
      return Color.black;
  };
  
  /**Retorna si está vivo
    @return siempre false
     */
  default boolean isVivo(){
      return false;
  }
  
  default int getVecinosVivos(){
      return 0;
  }
  
}
